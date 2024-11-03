**Views:**

No SQL views are used now, instead I use JPQL queries inside src/main/java/com/example/university/Repositories folder to create reports, and store results using objects of classes from src/main/java/com/example/university/ReprtDTOs.

**REST API reference (executed in Windows PowerShell):**

POST:
iwr -Method POST https://gobitsuha-university-sql-springboot.onrender.com/api/faculties/new -H @{'Content-Type' = 'application/json'} -Body '{"name": "ssss"}'
iwr -Method POST https://gobitsuha-university-sql-springboot.onrender.com/api/groups/new -H @{'Content-Type' = 'application/json'} -Body '{"name": "dl-1", "faculty": {"id": 502}}'

GET:
iwr -Method GET https://gobitsuha-university-sql-springboot.onrender.com/api/groups/352
iwr -Method GET https://gobitsuha-university-sql-springboot.onrender.com/api/reports/student-quantity-by-faculty

PUT:
iwr -Method PUT https://gobitsuha-university-sql-springboot.onrender.com/api/faculties/502 -H @{'Content-Type' = 'application/json'} -Body '{"name": "ssss1"}'

DELETE:
iwr -Method DELETE https://gobitsuha-university-sql-springboot.onrender.com/api/faculties/502

**Stored pocedures (used to auto delete from tables with foreign keys):**

1)clearAllByFaculty:
delimiter &&
create procedure clearAllByFaculty(fid BIGINT)
begin
delete student from student join u_group on student.u_group_id = u_group.id and u_group.faculty_id = fid;
delete from u_group where u_group.faculty_id = fid;
delete from faculty where faculty.id = fid;
end$$
delimiter ;

2)clearAllByGroup:
delimiter $$
create procedure clearAllByGroup(gid BIGINT)
begin delete from student where student.u_group_id = gid;
delete from u_group where u_group.id = gid;
end$$
delimiter ;
