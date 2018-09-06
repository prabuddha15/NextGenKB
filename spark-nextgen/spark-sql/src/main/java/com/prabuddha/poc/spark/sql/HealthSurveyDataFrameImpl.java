package com.prabuddha.poc.spark.sql;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.api.java.UDF1;
import org.apache.spark.sql.functions;
import org.apache.spark.sql.types.DataTypes;

import java.io.Serializable;

public class HealthSurveyDataFrameImpl implements Serializable {

    private static final String HEALTH_SURVEY_FILE_LOCATION = "/home/kyuts/IdeaProjects/git/NextGenKB/spark-nextgen/spark-sql/src/main/resources/survey.csv";

    private SparkSession sparkSession;

    public HealthSurveyDataFrameImpl() {
        sparkSession = SparkSession.builder().master("local").appName("spark-health-survey-sql").getOrCreate();
    }

    private void generateReportOnPeopleUnderwentTreatment() {

        Dataset initialDataset = loadHealthSurveyFile(HEALTH_SURVEY_FILE_LOCATION);
        Dataset filteredCSVDataset = initialDataset.select("Gender", "treatment");
        Dataset modifiedFilteredCSVDataset = filteredCSVDataset.select(filteredCSVDataset.col("Gender"),
                functions.when(filteredCSVDataset.col("treatment").equalTo("Yes"), 1).otherwise(0).alias("AllYes"),
                functions.when(filteredCSVDataset.col("treatment").equalTo("No"), 1).otherwise(0).alias("AllNo"));
        initialDataset.sparkSession().udf().register("parseGenderUDF", new UDF1<String, String>() {

            @Override
            public String call(String genderValue) throws Exception {
                switch (genderValue) {
                    case "male":
                    case "m":
                    case "male-ish":
                    case "maile":
                    case "mal":
                    case "man":
                    case "msle":
                    case "mail":
                    case "malr":
                        genderValue = "Male";
                        break;
                    case "female":
                    case "f":
                    case "woman":
                    case "women":
                    case "femal":
                    case "femsle":
                    case "femail":
                    case "femalr":
                        genderValue = "Female";
                        break;
                    default:
                        genderValue = "Transgender";
                        break;
                }
                return genderValue;
            }
        }, DataTypes.StringType);

        Dataset healthStatusTempDataset = modifiedFilteredCSVDataset.select(functions.callUDF("parseGenderUDF", functions.col("Gender")).alias("Gender"),
                functions.col("AllYes"), functions.col("AllNo"));

        Dataset healthStatusDatasetForAllGender = healthStatusTempDataset.groupBy("Gender").agg(functions.sum("AllYes"), functions.sum("AllNo"));

        Dataset healthStatusDatasetExceptTransgender = healthStatusDatasetForAllGender.filter(healthStatusDatasetForAllGender.col("Gender").notEqual("Transgender"));

        healthStatusDatasetForAllGender.show();
        healthStatusDatasetExceptTransgender.show();
    }

    private Dataset loadHealthSurveyFile(String fileLocation) {
        Dataset initialDataset = sparkSession.read()
                .format("com.databricks.spark.csv")
                .option("header", "true")
                .option("inferSchema", "true")
                .option("nullValue", "NA")
                .option("timestampFormat", "yyyy-MM-dd'T'HH:mm​:ss")
                .option("mode", "failfast")
                .option("path", fileLocation)
                .load();
        return initialDataset;
    }

    public static void main(String[] args) {
        HealthSurveyDataFrameImpl object = new HealthSurveyDataFrameImpl();
        object.generateReportOnPeopleUnderwentTreatment();
    }
}
