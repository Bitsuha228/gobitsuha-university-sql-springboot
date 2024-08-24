Views:

1)group_student_quantity
create view group_student_quantity as select u_group.name, count(student.name) as quantity from student right join u_group on student.u_group_id = u_group.id group by u_group.name;

2)faculty_student_quantity
create view  faculty_student_quantity as select faculty.name, count(*) as quantity from faculty, u_group, student where student.u_group_id = u_group.id and u_group.faculty_id = faculty.id group by faculty.name;

Stored pocedures:

1)clearAllByFaculty
delimiter &&
create procedure clearAllByFaculty(fid BIGINT)
begin
delete student from student join u_group on student.u_group_id = u_group.id and u_group.faculty_id = fid;
delete from u_group where u_group.faculty_id = fid;
delete from faculty where faculty.id = fid;
end$$
delimiter ;

2)clearAllByGroup
delimiter $$
create procedure clearAllByGroup(gid BIGINT)
begin delete from student where student.u_group_id = gid;
delete from u_group where u_group.id = gid;
end$$
delimiter ;
