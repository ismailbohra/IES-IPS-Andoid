package cmsr.ipsacademy.net.api

import cmsr.ipsacademy.net.activities.models.LoginModel
import cmsr.ipsacademy.net.activities.models.faculty.FacultyInfoModel
import cmsr.ipsacademy.net.activities.teacher.attendence.models.subjects.FacultySubjectDetailsModel
import cmsr.ipsacademy.net.activities.models.student.StudentInfoModel
import cmsr.ipsacademy.net.activities.teacher.attendence.models.lecture_category.LectureCategory
import cmsr.ipsacademy.net.activities.teacher.attendence.models.lecture_types.LectureTypes
import cmsr.ipsacademy.net.activities.teacher.attendence.models.students_by_batch_id.StudentByBatchId
import cmsr.ipsacademy.net.activities.teacher.attendence.models.time_slots.TimeSlots
import cmsr.ipsacademy.net.activities.teacher.attendence.models.topics.TopicsFromBatchId
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.GET

interface ApiSet {

    @FormUrlEncoded
    @POST("login_android.php")
    fun verifyUsers(
        @Field("computer_code") computer_code: String,
        @Field("password") password: String
    ): Call<LoginModel>

    @FormUrlEncoded
    @POST("student_college_info.php")
    fun getStudentDetails(
        @Field("computer_code") computer_code: String
    ): Call<StudentInfoModel>

    @FormUrlEncoded
    @POST("faculty_info.php")
    fun getFacultyDetails(
        @Field("computer_code") computer_code: String
    ): Call<FacultyInfoModel>

    @FormUrlEncoded
    @POST("faculty_class_attendance.php")
    fun getFacultySubjectsDetails(
        @Field("computer_code") computer_code: String
    ): Call<FacultySubjectDetailsModel>

    @FormUrlEncoded
    @POST("get_topic_from_batch_id.php")
    fun getTopicFromBatchId(
        @Field("clg_sub_code") clg_sub_code: String
    ): Call<TopicsFromBatchId>

    @GET("get_lecture_types.php")
    fun getLectureTypes(
    ): Call<LectureTypes>

    @GET("get_time_slots.php")
    fun getTimeSlots(
    ): Call<TimeSlots>

    @FormUrlEncoded
    @POST("get_lecture_category.php")
    fun getLectureCategory(
        @Field("clg_sub_code") clg_sub_code: String
    ): Call<LectureCategory>

    @FormUrlEncoded
    @POST("get_students_by_batch_id.php")
    fun getStudentsByBatchId(
        @Field("batch_id") batch_id: String,
        @Field("semester") semester: String
    ): Call<StudentByBatchId>

}