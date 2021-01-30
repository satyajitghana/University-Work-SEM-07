INSERT INTO `STUDENT_LOGIN` (`id`, `user_name`, `hashed_password`) VALUES
(1, '17ETCS002159', '$2b$10$uVRx4ogFBi0owMljpvEilONnd9wWOMtrpgVwqw2Mw8.aNmo6yEU1u'),
(2, '17ETCS002122', '$2b$10$ATp9qxsPWBsOUXDAB1YvK.yTLi4GK1mzpIHBCfSOCQwtxLU/52Pk2'),
(3, '17ETCS002168', '$2b$10$3cfBMD3yRi3YJk.fFGrNY.Yx1RRonj4z2cqgOe2fgZ78yNaqRxFkC');

INSERT INTO `STUDENT` (`id`, `reg_no`, `name`, `department`, `course`, `contact_no`) VALUES
(2, '17ETCS002122', 'Prachi Poddar', 'CSE', 'B.Tech', '9856523658'),
(1, '17ETCS002159', 'Satyajit Ghana', 'CSE', 'B.Tech', '7892137665'),
(3, '17ETCS002168', 'Shikhar Singh', 'CSE', 'B.Tech', '9852145896');

INSERT INTO `PROJEKT` (`id`, `project_leader_regno`, `project_name`, `mentor_name`, `department`, `category`) VALUES
(2, '17ETCS002159', 'KrishiAI', 'Chaitra S', 'CSE', 'DL');


INSERT INTO `PROJECT_STUDENT_REGISTER` (`project_id`, `student_reg_no`) VALUES
(2, '17ETCS002159'),
(2, '17ETCS002122'),
(2, '17ETCS002168');


INSERT INTO `EXHIBITION` (`room_id`, `room_name`, `capacity`) VALUES
(1, 'A201', 60);
