INSERT INTO teams (name) VALUES('EHCI'), ('SIGMA');

INSERT INTO offices (team_id, name) VALUES	(1, 'B206'), (1, 'B203'),
											(1, 'B204'), (1, 'B207'),
											(1, 'B205'), (1, 'B209') ;
											
INSERT INTO members (id, firstName, lastName, email, office_id, photo_url, position, mentor_id) VALUES
	(1, 'Renaud', 'Blanch', 'Renaud.Blanch@imag.fr', 6, 'TeamImages/renaud.blanch_.jpeg', 'permanent', NULL),
	(2, 'Gaëlle', 'Calvary', 'Gaelle.Calvary@imag.fr', 1, 'TeamImages/gaelle.calvary.jpg', 'permanent', NULL),
	(3, 'Joëlle', 'Coutaz', 'Joelle.Coutaz@imag.fr', 1, 'TeamImages/joelle.coutaz.jpg', 'team_leader', NULL),
	(4, 'Jorge-Luis', 'Pérez Medina', 'Jorge-Luis.Perez-Medina@imag.fr', 2, 'TeamImages/jorge.perez.jpg', 'phd_student', 10),
	(5, 'Laurence', 'Nigay', 'Laurence.Nigay@imag.fr', 5, 'TeamImages/laurence.nigay.gif', 'permanent', NULL),
	(6, 'Marcos', 'Serrano', 'Marcos.Serrano@imag.fr', 5, 'TeamImages/Marcos_iihm.jpg', 'phd_student', 5),
	(7, 'Céline', 'Coutrix', 'Celine.Coutrix@imag.fr', 5, 'TeamImages/MyPicture_2_2.jpg', 'phd_student', 5),
	(8, 'Adriano', 'Scoditti', 'Adriano.Scoditti@imag.fr', 6, 'TeamImages/Photo_5.jpg', 'phd_student', 1),
	(9, 'Gilles', 'Bailly', 'Gilles.Bailly@imag.fr', 5, 'TeamImages/photo.jpg', 'phd_student', 5),
	(10, 'Sophie', 'Dupuy-Chessa', 'Sophie.Dupuy-Chessa@imag.fr', 2, 'TeamImages/photoDupuy2.jpg', 'permanent', NULL),
	(11, 'François', 'Bérard', 'Francois.Berard@imag.fr', 6, 'TeamImages/smile_.jpg', 'permanent', NULL),
	(12, 'Yann', 'Laurillau', 'Yann.Laurillau@imag.fr', 4, 'TeamImages/yann-cv.jpg', 'permanent', NULL) ;