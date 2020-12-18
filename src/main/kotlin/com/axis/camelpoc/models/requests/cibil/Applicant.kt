package com.axis.camelpoc.models.requests.cibil

import com.fasterxml.jackson.annotation.JsonProperty

data class Applicant(

        @JsonProperty("ApplicantType")
        val applicantType: String? = null,

        @JsonProperty("ApplicantFirstName")
        val applicantFirstName: String? = null,

        @JsonProperty("ApplicantLastName")
        val applicantLastName: String? = null,

        @JsonProperty("ApplicantMiddleName")
        val applicantMiddleName: String? = null,

        @JsonProperty("Gender")
        val gender: String? = null,

        @JsonProperty("KeyPersonName")
        val keyPersonName: String? = null,

        @JsonProperty("Address")
        val address: Address? = null,

        @JsonProperty("CompanyName")
        val companyName: String? = null,

        @JsonProperty("Identifiers")
        val identifiers: List<Identifier?>? = null,

        @JsonProperty("MemberOtherId3Type")
        val memberOtherId3Type: String? = null,

        @JsonProperty("MemberRelationName2")
        val memberRelationName2: String? = null,

        @JsonProperty("MemberRelationName1")
        val memberRelationName1: String? = null,

        @JsonProperty("MemberRelationName4")
        val memberRelationName4: String? = null,

        @JsonProperty("MemberRelationName3")
        val memberRelationName3: String? = null,

        @JsonProperty("NomineeName")
        val nomineeName: String? = null,

        @JsonProperty("DateOfBirth")
        val dateOfBirth: String? = null,

        @JsonProperty("MemberOtherId2Type")
        val memberOtherId2Type: String? = null,

        @JsonProperty("MemberRelationType1")
        val memberRelationType1: String? = null,

        @JsonProperty("MemberRelationType3")
        val memberRelationType3: String? = null,

        @JsonProperty("MemberRelationType2")
        val memberRelationType2: String? = null,

        @JsonProperty("MemberRelationType4")
        val memberRelationType4: String? = null,

        @JsonProperty("EmailAddress")
        val emailAddress: String? = null,

        @JsonProperty("MemberOtherId3")
        val memberOtherId3: String? = null,

        @JsonProperty("Telephones")
        val telephones: List<Telephone?>? = null,

        @JsonProperty("MemberOtherId1")
        val memberOtherId1: String? = null,

        @JsonProperty("MemberOtherId2")
        val memberOtherId2: String? = null,

        @JsonProperty("MemberOtherId1Type")
        val memberOtherId1Type: String? = null,

        @JsonProperty("NomineeRelation")
        val nomineeRelation: String? = null,

        @JsonProperty("KeyPersonRelation")
        val keyPersonRelation: String? = null,

        @JsonProperty("Accounts")
        val accounts: List<Account?>? = null
)