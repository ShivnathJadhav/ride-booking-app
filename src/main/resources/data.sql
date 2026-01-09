INSERT INTO app_user (id, name, email, password)
VALUES (1, 'Amit Sharma', 'amit.sharma@example.com', 'password123');

INSERT INTO user_roles (user_id, roles)
VALUES (1, 'RIDER');

INSERT INTO rider (id, user_id, rating)
VALUES (1, 1, 4.8);

INSERT INTO app_user (id, name, email, password) VALUES
(2, 'Rohit Patil', 'rohit.patil@example.com', 'password123'),
(3, 'Sandeep Kulkarni', 'sandeep.kulkarni@example.com', 'password123'),
(4, 'Mahesh Shinde', 'mahesh.shinde@example.com', 'password123'),
(5, 'Vikas Jadhav', 'vikas.jadhav@example.com', 'password123'),
(6, 'Akash Pawar', 'akash.pawar@example.com', 'password123'),
(7, 'Nitin Deshmukh', 'nitin.deshmukh@example.com', 'password123'),
(8, 'Swapnil More', 'swapnil.more@example.com', 'password123'),
(9, 'Pravin Thorat', 'pravin.thorat@example.com', 'password123'),
(10, 'Ganesh Bhosale', 'ganesh.bhosale@example.com', 'password123'),
(11, 'Suhas Kamble', 'suhas.kamble@example.com', 'password123'),
(12, 'Sagar Chavan', 'sagar.chavan@example.com', 'password123'),
(13, 'Ramesh Gaikwad', 'ramesh.gaikwad@example.com', 'password123'),
(14, 'Sunil Nikam', 'sunil.nikam@example.com', 'password123'),
(15, 'Shubham Raut', 'shubham.raut@example.com', 'password123'),
(16, 'Deepak Kadam', 'deepak.kadam@example.com', 'password123'),
(17, 'Sanket Salunkhe', 'sanket.salunkhe@example.com', 'password123'),
(18, 'Tejas Patil', 'tejas.patil@example.com', 'password123'),
(19, 'Kiran Ghadge', 'kiran.ghadge@example.com', 'password123'),
(20, 'Omkar Sathe', 'omkar.sathe@example.com', 'password123'),
(21, 'Amol Lokhande', 'amol.lokhande@example.com', 'password123'),
(22, 'Abhishek Khare', 'abhishek.khare@example.com', 'password123'),
(23, 'Rahul Singh', 'rahul.singh@example.com', 'password123'),
(24, 'Aditya Bhise', 'aditya.bhise@example.com', 'password123'),
(25, 'Mandar Dixit', 'mandar.dixit@example.com', 'password123'),
(26, 'Sharad Bhandari', 'sharad.bhandari@example.com', 'password123'),
(27, 'Soham Phadke', 'soham.phadke@example.com', 'password123'),
(28, 'Harshad Nair', 'harshad.nair@example.com', 'password123'),
(29, 'Naveen Shetty', 'naveen.shetty@example.com', 'password123'),
(30, 'Yuvraj Kale', 'yuvraj.kale@example.com', 'password123'),
(31, 'Ruturaj Kolhe', 'ruturaj.kolhe@example.com', 'password123');


INSERT INTO user_roles (user_id, roles) VALUES
(2,'DRIVER'),(3,'DRIVER'),(4,'DRIVER'),(5,'DRIVER'),(6,'DRIVER'),
(7,'DRIVER'),(8,'DRIVER'),(9,'DRIVER'),(10,'DRIVER'),(11,'DRIVER'),
(12,'DRIVER'),(13,'DRIVER'),(14,'DRIVER'),(15,'DRIVER'),(16,'DRIVER'),
(17,'DRIVER'),(18,'DRIVER'),(19,'DRIVER'),(20,'DRIVER'),(21,'DRIVER'),
(22,'DRIVER'),(23,'DRIVER'),(24,'DRIVER'),(25,'DRIVER'),(26,'DRIVER'),
(27,'DRIVER'),(28,'DRIVER'),(29,'DRIVER'),(30,'DRIVER'),(31,'DRIVER');


INSERT INTO driver (id, user_id, rating, available, current_location) VALUES
(1, 2, 4.6, true, ST_SetSRID(ST_MakePoint(73.7286, 18.5918), 4326)), -- Hinjewadi Phase 1
(2, 3, 4.4, true, ST_SetSRID(ST_MakePoint(73.7758, 18.5590), 4326)), -- Baner
(3, 4, 4.8, true, ST_SetSRID(ST_MakePoint(73.7822, 18.5970), 4326)), -- Balewadi
(4, 5, 4.3, true, ST_SetSRID(ST_MakePoint(73.7865, 18.5912), 4326)), -- Wakad
(5, 6, 4.7, true, ST_SetSRID(ST_MakePoint(73.8437, 18.5709), 4326)), -- Aundh
(6, 7, 4.2, true, ST_SetSRID(ST_MakePoint(73.8567, 18.5204), 4326)), -- Shivajinagar
(7, 8, 4.9, true, ST_SetSRID(ST_MakePoint(73.8897, 18.5180), 4326)), -- Koregaon Park
(8, 9, 4.5, true, ST_SetSRID(ST_MakePoint(73.9123, 18.5203), 4326)), -- Kalyani Nagar
(9, 10, 4.1, true, ST_SetSRID(ST_MakePoint(73.9344, 18.5667), 4326)), -- Viman Nagar
(10, 11, 4.6, true, ST_SetSRID(ST_MakePoint(73.9044, 18.5016), 4326)), -- Magarpatta
(11, 12, 4.3, true, ST_SetSRID(ST_MakePoint(73.9221, 18.5089), 4326)), -- Hadapsar
(12, 13, 4.0, true, ST_SetSRID(ST_MakePoint(73.8077, 18.5074), 4326)), -- Kothrud
(13, 14, 4.7, true, ST_SetSRID(ST_MakePoint(73.8500, 18.4800), 4326)), -- Bibwewadi
(14, 15, 4.2, true, ST_SetSRID(ST_MakePoint(73.8645, 18.4575), 4326)), -- Kondhwa
(15, 16, 4.8, true, ST_SetSRID(ST_MakePoint(73.8201, 18.6298), 4326)), -- Pimple Saudagar
(16, 17, 4.4, true, ST_SetSRID(ST_MakePoint(73.8021, 18.6496), 4326)), -- Nigdi
(17, 18, 4.5, true, ST_SetSRID(ST_MakePoint(73.8490, 18.6500), 4326)), -- Pimpri
(18, 19, 4.1, true, ST_SetSRID(ST_MakePoint(73.8561, 18.6290), 4326)), -- Chinchwad
(19, 20, 4.6, true, ST_SetSRID(ST_MakePoint(73.7411, 18.5901), 4326)), -- Hinjewadi Phase 3
(20, 21, 4.3, true, ST_SetSRID(ST_MakePoint(73.7760, 18.5200), 4326)), -- University Road
(21, 22, 4.7, true, ST_SetSRID(ST_MakePoint(73.8110, 18.5405), 4326)), -- Pashan
(22, 23, 4.5, true, ST_SetSRID(ST_MakePoint(73.8900, 18.5400), 4326)), -- Mundhwa
(23, 24, 4.2, true, ST_SetSRID(ST_MakePoint(73.7700, 18.5000), 4326)), -- Karve Nagar
(24, 25, 4.8, true, ST_SetSRID(ST_MakePoint(73.7601, 18.5800), 4326)), -- Sus Road
(25, 26, 4.0, true, ST_SetSRID(ST_MakePoint(73.8333, 18.6111), 4326)), -- Bopodi
(26, 27, 4.9, true, ST_SetSRID(ST_MakePoint(73.8400, 18.5500), 4326)), -- Khadki
(27, 28, 4.3, true, ST_SetSRID(ST_MakePoint(73.7705, 18.6100), 4326)), -- Marunji
(28, 29, 4.6, true, ST_SetSRID(ST_MakePoint(73.7484, 18.6021), 4326)), -- Maan
(29, 30, 4.4, true, ST_SetSRID(ST_MakePoint(73.8600, 18.5300), 4326)), -- Camp Area
(30, 31, 4.7, true, ST_SetSRID(ST_MakePoint(73.7300, 18.5900), 4326)); -- Hinjewadi IT Park

