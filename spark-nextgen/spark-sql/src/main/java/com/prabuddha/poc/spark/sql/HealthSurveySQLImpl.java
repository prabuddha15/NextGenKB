package com.prabuddha.poc.spark.sql;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.api.java.UDF1;
import org.apache.spark.sql.functions;
import org.apache.spark.sql.types.DataTypes;

import java.io.Serializable;

public class HealthSurveySQLImpl implements Serializable {

    private static final String HEALTH_SURVEY_FILE_LOCATION = "/home/kyuts/IdeaProjects/git/NextGenKB/spark-nextgen/spark-sql/src/main/resources/survey.csv";

    private SparkSession sparkSession;

    public HealthSurveySQLImpl() {
        sparkSession = SparkSession.builder().master("local").appName("spark-health-survey-sql").getOrCreate();
    }

    private void generateReportOnPeopleUnderwentTreatment() {

        Dataset initialDataset = loadHealthSurveyFile(HEALTH_SURVEY_FILE_LOCATION);
        initialDataset.createOrReplaceTempView("HEALTH_SURVEY");

        sparkSession.sql("select gender, sum(yes), sum(no) \n" +
                "            from (select case when lower(trim(gender)) in ('male','m','male-ish','maile','mal',\n" +
                "                                                           'male (cis)','make','male ','man','msle',\n" +
                "                                                           'mail','malr','cis man','cis male') \n" +
                "                              then 'Male' \n" +
                "                              when lower(trim(gender)) in ('cis female','f','female','woman',\n" +
                "                                                           'female','female ','cis-female/femme',\n" +
                "                                                           'female (cis)','femail') \n" +
                "                              then 'Female'\n" +
                "                              else 'Transgender' \n" +
                "                              end as gender,\n" +
                "                              case when treatment == 'Yes' then 1 else 0 end as yes,\n" +
                "                              case when treatment == 'No' then 1 else 0 end as no\n" +
                "                  from HEALTH_SURVEY) \n" +
                "         where gender != 'Transgender'\n" +
                "         group by gender").show();
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
        HealthSurveySQLImpl object = new HealthSurveySQLImpl();
        object.generateReportOnPeopleUnderwentTreatment();
    }

}
