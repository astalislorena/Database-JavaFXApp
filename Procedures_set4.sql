#Procedura modificata

DELIMITER //
CREATE PROCEDURE add_course(new_title varchar(30), new_maximum_students int, new_course_description varchar(200))
BEGIN
	start transaction;
			INSERT INTO course(title, max_number_of_students, description)
			VALUES (new_title, new_maximum_students, new_course_description);
	commit;
END //
DELIMITER ;

call add_course('Istorie',20,'nou Adaugare curs');

DELIMITER //
CREATE PROCEDURE enroll_student(cnpUser varchar(13),name varchar(30))
BEGIN 
        select @nr_student := number_of_courses from student where student.student_CNP = cnpUser;#pentru a creste nr de cursuri
        select @nr_max := max_number_of_students from course where name = course.title ;#numar maxim de studenti posibili la un curs
        select @nr_min_courses := count(professor_CNP)  from activity;#profesorul cu cei mai putin studenti
        select @id_professor := professor_CNP from activity where professor_CNP in(select count(professor_CNP)  from activity) limit 1;#cnp profesor cu cei mai putin studenti
        select @nr_max_courses := max_number_of_courses from professor where @id_professor = professor.professor_CNP;#pentru a nu depasi numarul de cursusri al unui profesor
		select @id_course := course_ID from course where name = course.title;
        
        START TRANSACTION;
        
			if @nr_min_courses < @nr_max_courses then 
            update student set number_of_courses = number_of_courses + 1 where student.student_CNP = cnpUser;
			update professor set min_number_of_courses = min_number_of_courses + 1 where professor_CNP = @id_professor;
			end if;
            
		insert into activity(professor_CNP,student_CNP,course_ID,type,professor_role,start_date,end_date)
        values ( @id_professor,cnpUser,@id_course,type,professor_role,"2012-08-12","2013-01-12");
		COMMIT;
END //
DELIMITER ;

call enroll_student('5000890890789',"Databases");

DELIMITER //
CREATE PROCEDURE enroll_professor(cnp varchar(13),role varchar(15),name varchar(30))
BEGIN
		START TRANSACTION;
        select @id_course := course.course_ID from course where name = course.title;
        
		INSERT INTO professor_enrollment(professor_CNP,professor_role,course_ID)
        VALUES(cnp,role,@id_course);

		COMMIT;
END //
DELIMITER ;

call enroll_professor("1000100100101", 'seminarist',"Databases");

##///////

DELIMITER //
CREATE PROCEDURE delete_student(student_cnp varchar(13))
BEGIN
		START TRANSACTION;
		Delete from group_activity where student_cnp = group_activity.student_CNP limit 1;
		Delete from grade_book where student_cnp = grade_book.student_CNP limit 1;
		Delete from student_enrollement where student_cnp = student_enrollement.student_CNP limit 1;
		Delete from activity where student_cnp = activity.student_CNP limit 1;
		Delete from student where student_cnp = student.student_CNP limit 1;
		Delete from person where student_cnp = CNP;
		COMMIT;
END //
DELIMITER ;

call delete_student('5000123124668');

DELIMITER //
CREATE PROCEDURE delete_professor(professor_cnp varchar(13))
BEGIN
		START TRANSACTION;
		Delete from study_group where professor_cnp = study_group.professor_CNP limit 1;
		Delete from grade_book where professor_cnp = grade_book.professor_CNP limit 1;
		Delete from professor_enrollment where professor_cnp = professor_enrollment.professor_CNP limit 1;
		Delete from activity where professor_cnp = activity.professor_CNP limit 1;
		Delete from professor where professor_cnp = professor.professor_CNP limit 1;
		Delete from person where professor_cnp = CNP;
		COMMIT;
END //
DELIMITER ;

call delete_professor('1000100100101');

DELIMITER //
CREATE PROCEDURE delete_admin(admin_cnp varchar(13))
BEGIN
		START TRANSACTION;
		Delete from admin where admin_cnp = admin.admin_CNP limit 1;
		Delete from person where admin_cnp = CNP;
		COMMIT;
END //
DELIMITER ;

call delete_admin('1000123123123');

DELIMITER //
CREATE PROCEDURE update_student(student_cnp varchar(13), student_first_name varchar(30), student_last_name varchar(30), student_email varchar(30), student_user_password varchar(20), student_iban varchar(34), student_telephone_number varchar(12), student_contact_number varchar(12), student_address varchar(50))
BEGIN
	START TRANSACTION;
        update person set first_name = student_first_name,
        last_name = student_last_name,
        email = student_email,
        password = student_user_password,
		iban = student_iban,
        telephone_number= student_telephone_number,
        address = student_address,
        contact_number=student_contact_number where student_cnp = cnp;
        COMMIT;
END //
DELIMITER ;

call update_student('5000890890789','Anca','Whittle','whittle_rebecca@student.com','pas', 'NL14INGB6333227641','2025550107','', 'Flowers street, number 4');

DELIMITER //
CREATE PROCEDURE update_professor(professor_cnp varchar(13), professor_first_name varchar(30), professor_last_name varchar(30), professor_email varchar(30), professor_user_password varchar(20), professor_iban varchar(34), professor_telephone_number varchar(12), professor_contact_number varchar(12), professor_address varchar(50))
BEGIN
	START TRANSACTION;
        update person set first_name = professor_first_name,
        last_name = professor_last_name,
        email = professor_email,
        password = professor_user_password,
		iban = professor_iban,
        telephone_number= professor_telephone_number,
        address = professor_address,
        contact_number=professor_contact_number where professor_cnp = cnp;
        COMMIT;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE update_admin(admin_cnp varchar(13), admin_first_name varchar(30), admin_last_name varchar(30), admin_email varchar(30), admin_user_password varchar(20),admin_iban varchar(34),admin_telephone_number varchar(12), admin_contact_number varchar(12), admin_address varchar(50))
BEGIN
	START TRANSACTION;
        update person set first_name = admin_first_name,
        last_name = admin_last_name,
        email = admin_email,
        user_password = admin_user_password,
		iban = admin_iban,
        telephone_number= admin_telephone_number,
        address = admin_address,
        contact_number=admin_contact_number where admin_cnp = cnp;
        COMMIT;
END //
DELIMITER ;

##Proceduri adaugate
DELIMITER //
CREATE PROCEDURE search_course(name varchar(30)) 
BEGIN
	SELECT *
    FROM course
    WHERE name = course.title;
END //
DELIMITER 

call search_course('Istorie');

DELIMITER //
CREATE PROCEDURE delete_course(name varchar(30)) 
BEGIN
	START TRANSACTION;
	DELETE
    FROM course
    WHERE name = course.title;
    COMMIT;
END //
DELIMITER 

call delete_course('Istorie');

DELIMITER //
CREATE PROCEDURE update_course(course_ID int,name varchar(30),max_number_of_students int,description VARCHAR(100)) 
BEGIN
	START TRANSACTION;
	UPDATE course set 
    title = name,
    max_number_of_students = max_number_of_students,
    description = description where course_ID = course.course_ID;
   COMMIT;
END //
DELIMITER 

call update_course(4,'Istorie',100,"Curs test");


DELIMITER //
CREATE PROCEDURE add_student_study_group(cnpUser varchar(13),name varchar(30)) 
BEGIN
	START TRANSACTION;
    select @id := study_group_ID from study_group where name  = study_group.name;
	INSERT INTO student_enrollement(study_group_ID,student_CNP)
			VALUES (@id,cnpUser);
   COMMIT;
END //
DELIMITER 

call add_student_study_group('5000890890789',"ana are mere");

DELIMITER //
CREATE PROCEDURE delete_student_study_group(cnpUser varchar(13),name varchar(30)) 
BEGIN
	START TRANSACTION;
	delete from student_enrollement where cnpUser = student_enrollement.student_CNP;
   COMMIT;
END //
DELIMITER 

call delete_student_study_group('5000890890789',1);

DELIMITER //
CREATE PROCEDURE future_activities_student(student_CNP VARCHAR(13)) 
BEGIN
    SELECT date_and_time, is_graded, grade_weight, grade, (SELECT activity.type FROM activity WHERE activity.activity_ID = scheduled_activity.activity_ID AND activity.student_CNP = student_CNP) AS activity_type
	FROM scheduled_activity
	WHERE DATE(date_and_time) > current_date();
END //
DELIMITER ;

CALL future_activities_student("3000123123123");

DELIMITER //
CREATE PROCEDURE future_activities_professor(professor_CNP VARCHAR(13)) 
BEGIN
    SELECT date_and_time, is_graded, grade_weight, grade, (SELECT activity.type FROM activity WHERE activity.activity_ID = scheduled_activity.activity_ID AND activity.professor_CNP = professor_CNP) AS activity_type
	FROM scheduled_activity
	WHERE DATE(date_and_time) > current_date();
END //
DELIMITER ;

CALL future_activities_professor("1000100100100");

#Noi proceduri adaugate

DELIMITER //
CREATE PROCEDURE add_study_group(course_ID int,name VARCHAR(30))
BEGIN
	start transaction;
    select @id := professor_CNP from activity where course_ID = activity.course_ID;
	INSERT INTO study_group(course_ID,professor_CNP,name)
			VALUES (course_ID,@id,name);
	commit;
END //
DELIMITER ;

call add_study_group(3,"ana are mere");

DELIMITER //
CREATE PROCEDURE delete_study_group(name VARCHAR(30))
BEGIN
	start transaction;
			SELECT @ID := study_group_ID FROM study_group WHERE study_group.name =  name ;
            
            delete from group_activity where group_activity.study_group_ID = @ID;
            delete from message where message.study_group_ID = @ID;
            delete from student_enrollement where @ID = student_enrollement.study_group_ID;
			delete from study_group where @ID = study_group.study_group_ID;
             
	commit;
END //
DELIMITER ;

call delete_study_group("Great Grades for DB");

DELIMITER //
CREATE PROCEDURE search_study_group(name VARCHAR(30))
BEGIN
	SELECT *
    FROM study_group
    WHERE name = study_group.name;
END //
DELIMITER ;

call search_study_group("ana");

DELIMITER //
CREATE PROCEDURE view_search_course_professor(CNP VARCHAR(13)) 
BEGIN
	select @id := professor_CNP from activity where CNP = activity.professor_CNP;
    SELECT title
	FROM course
	WHERE CNP = @id;
END //
DELIMITER ;

CALL view_search_course_professor("1000100100102");

DELIMITER //
CREATE PROCEDURE view_search_course_student(CNP VARCHAR(13)) 
BEGIN
	select @id := student_CNP from activity where CNP = activity.student_CNP;
    SELECT title
	FROM course
	WHERE CNP = @id;
END //
DELIMITER ;

CALL view_search_course_student("5000890890789");

