package com.prabuddha.poc.design.builder;

/*
Note:
-   FootballerBuilder is the outer class which is having a static inner class.
-   The benefit of using a static inner class is that static inner class can instantiate an outer class which is having
    a private constructor. You can not do the same with a non-static inner class as you need to have a object of the outer
    class created before accessing the non-static inner class.
-

 */

public class FootballerBuilder {

    private final String firstName;
    private final String lastName;
    private final int age;
    private String clubName;
    private String position;

    private FootballerBuilder(Builder builder) {
        firstName = builder.firstNameInnerClassMember;
        lastName = builder.lastNameInnerClassMember;
        age = builder.ageInnerClassMember;
        clubName = builder.clubNameInnerClassMember;
        position = builder.positionInnerClassMember;
    }

    public void printFootballerDetails(){
        System.out.println(firstName + " " +lastName+ " : "+clubName+ " >> "+position);
    }

    public static class Builder {

        private final String firstNameInnerClassMember;
        private final String lastNameInnerClassMember;
        private final int ageInnerClassMember;
        private String clubNameInnerClassMember;
        private String positionInnerClassMember;

        public Builder(String firstNameInnerClassMember, String lastNameInnerClassMember, int ageInnerClassMember) {
            this.firstNameInnerClassMember = firstNameInnerClassMember;
            this.lastNameInnerClassMember = lastNameInnerClassMember;
            this.ageInnerClassMember = ageInnerClassMember;
        }

        public Builder clubName(String value) {
            clubNameInnerClassMember = value;
            return this;
        }

        public Builder position(String value) {
            positionInnerClassMember = value;
            return this;
        }

        public FootballerBuilder build() {
            return new FootballerBuilder(this);
        }
    }

}
