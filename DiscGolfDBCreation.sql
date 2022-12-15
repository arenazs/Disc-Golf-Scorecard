DROP TABLE IF EXISTS course, hole, pin, course_hole, hole_pin, game, player, game_player CASCADE;

CREATE TABLE course(
	course_id SERIAL,
	coures_name VARCHAR(100) NOT NULL,
	
	CONSTRAINT pk_course PRIMARY KEY (course_id)
);

CREATE TABLE hole(
	hole_id SERIAL,
	course_id INT NOT NULL,
	hole_number INT CHECK (hole_number > 0) NOT NULL,
	current_pin_position INT CHECK (current_pin_position >= 0 AND current_pin_position <= 4) NOT NULL,
	
	CONSTRAINT pk_hole PRIMARY KEY (hole_id),
	CONSTRAINT fk_course_id FOREIGN KEY (course_id) REFERENCES course(course_id)
);

CREATE TABLE pin(
	pin_id SERIAL,
	hole_id INT NOT NULL,
	pin_position INT CHECK (pin_position >= 0 AND pin_position <= 4) NOT NULL,
	distance INT NOT NULL,
	par INT CHECK (par > 2 AND par < 6) NOT NULL,
	
	CONSTRAINT pk_pin PRIMARY KEY (pin_id),
	CONSTRAINT fk_hole_id FOREIGN KEY (hole_id) REFERENCES hole(hole_id)
);

CREATE TABLE course_hole (
	course_id INT NOT NULL,
	hole_id INT NOT NULL,
	
	CONSTRAINT pk_course_hole PRIMARY KEY (course_id, hole_id),
	CONSTRAINT fk_course_id FOREIGN KEY (course_id) REFERENCES course(course_id),
	CONSTRAINT fk_hole_id FOREIGN KEY (hole_id) REFERENCES hole(hole_id)
);

CREATE TABLE hole_pin(
	hole_id INT NOT NULL,
	pin_id INT NOT NULL,
	
	CONSTRAINT pk_hole_pin PRIMARY KEY (hole_id, pin_id),
	CONSTRAINT fk_hole_id FOREIGN KEY (hole_id) REFERENCES hole(hole_id),
	CONSTRAINT fk_pin_id FOREIGN KEY (pin_id) REFERENCES pin(pin_id)
);

CREATE TABLE game(
	game_id SERIAL,
	course_id INT NOT NULL,
	date_played DATE NOT NULL,
	start_time TIME NOT NULL,
	end_time TIME,
	
	CONSTRAINT pk_game_id PRIMARY KEY (game_id),
	CONSTRAINT fk_course_id FOREIGN KEY (course_id) REFERENCES course(course_id)
);

CREATE TABLE player(
	player_id SERIAL,
	player_name VARCHAR(100) NOT NULL,
	games_played INT DEFAULT 0,
	
	CONSTRAINT pk_player_id PRIMARY KEY (player_id)
);

CREATE TABLE game_player(
	game_id INT NOT NULL,
	player_id INT NOT NULL,
	player_score INT NOT NULL,
	
	CONSTRAINT pk_game_player PRIMARY KEY (game_id, player_id),
	CONSTRAINT fk_game_id FOREIGN KEY (game_id) REFERENCES game(game_id),
	CONSTRAINT fk_player_id FOREIGN KEY (player_id) REFERENCES player(player_id)
);

INSERT INTO course VALUES (DEFAULT, 'Fort Washington');
INSERT INTO course VALUES (DEFAULT, 'Tamanend');
INSERT INTO course VALUES (DEFAULT, 'Tyler State Park');

INSERT INTO hole VALUES (DEFAULT, 1, 1, 1);
INSERT INTO hole VALUES (DEFAULT, 1, 2, 0);
INSERT INTO hole VALUES (DEFAULT, 1, 3, 0);
INSERT INTO hole VALUES (DEFAULT, 1, 4, 0);
INSERT INTO hole VALUES (DEFAULT, 1, 5, 1);
INSERT INTO hole VALUES (DEFAULT, 1, 6, 0);
INSERT INTO hole VALUES (DEFAULT, 1, 7, 0);
INSERT INTO hole VALUES (DEFAULT, 1, 8, 1);
INSERT INTO hole VALUES (DEFAULT, 1, 9, 0);
INSERT INTO hole VALUES (DEFAULT, 1, 10, 1);
INSERT INTO hole VALUES (DEFAULT, 1, 11, 0);
INSERT INTO hole VALUES (DEFAULT, 1, 12, 0);
INSERT INTO hole VALUES (DEFAULT, 1, 13, 0);
INSERT INTO hole VALUES (DEFAULT, 1, 14, 1);
INSERT INTO hole VALUES (DEFAULT, 1, 15, 0);
INSERT INTO hole VALUES (DEFAULT, 1, 16, 1);
INSERT INTO hole VALUES (DEFAULT, 1, 17, 0);
INSERT INTO hole VALUES (DEFAULT, 1, 18, 0);

INSERT INTO pin VALUES (DEFAULT, 1, 0, 112, 3);
INSERT INTO pin VALUES (DEFAULT, 1, 1, 153, 3);
INSERT INTO pin VALUES (DEFAULT, 2, 0, 320, 3);
INSERT INTO pin VALUES (DEFAULT, 2, 1, 346, 3);
INSERT INTO pin VALUES (DEFAULT, 3, 0, 270, 3);
INSERT INTO pin VALUES (DEFAULT, 3, 1, 369, 3);
INSERT INTO pin VALUES (DEFAULT, 4, 0, 317, 3);
INSERT INTO pin VALUES (DEFAULT, 4, 1, 512, 4);
INSERT INTO pin VALUES (DEFAULT, 5, 0, 274, 3);
INSERT INTO pin VALUES (DEFAULT, 5, 1, 448, 3);
INSERT INTO pin VALUES (DEFAULT, 6, 0, 436, 3);
INSERT INTO pin VALUES (DEFAULT, 6, 1, 505, 4);
INSERT INTO pin VALUES (DEFAULT, 7, 0, 337, 3);
INSERT INTO pin VALUES (DEFAULT, 7, 1, 525, 4);
INSERT INTO pin VALUES (DEFAULT, 8, 0, 325, 3);
INSERT INTO pin VALUES (DEFAULT, 8, 1, 442, 4);
INSERT INTO pin VALUES (DEFAULT, 9, 0, 186, 3);
INSERT INTO pin VALUES (DEFAULT, 9, 1, 363, 3);
INSERT INTO pin VALUES (DEFAULT, 10, 0, 259, 3);
INSERT INTO pin VALUES (DEFAULT, 10, 1, 570, 5);
INSERT INTO pin VALUES (DEFAULT, 11, 0, 179, 3);
INSERT INTO pin VALUES (DEFAULT, 11, 1, 326, 3);
INSERT INTO pin VALUES (DEFAULT, 12, 1, 226, 3);
INSERT INTO pin VALUES (DEFAULT, 12, 1, 307, 3);
INSERT INTO pin VALUES (DEFAULT, 13, 0, 315, 3);
INSERT INTO pin VALUES (DEFAULT, 13, 1, 572, 4);
INSERT INTO pin VALUES (DEFAULT, 14, 0, 126, 3);
INSERT INTO pin VALUES (DEFAULT, 14, 1, 197, 3);
INSERT INTO pin VALUES (DEFAULT, 15, 0, 203, 3);
INSERT INTO pin VALUES (DEFAULT, 15, 1, 271, 3);
INSERT INTO pin VALUES (DEFAULT, 16, 0, 303, 3);
INSERT INTO pin VALUES (DEFAULT, 16, 1, 295, 3);
INSERT INTO pin VALUES (DEFAULT, 17, 0, 290, 3);
INSERT INTO pin VALUES (DEFAULT, 17, 1, 379, 4);
INSERT INTO pin VALUES (DEFAULT, 18, 0, 297, 3);
INSERT INTO pin VALUES (DEFAULT, 18, 1, 491, 4);

INSERT INTO course_hole VALUES (1,1);
INSERT INTO course_hole VALUES (1,2);
INSERT INTO course_hole VALUES (1,3);
INSERT INTO course_hole VALUES (1,4);
INSERT INTO course_hole VALUES (1,5);
INSERT INTO course_hole VALUES (1,6);
INSERT INTO course_hole VALUES (1,7);
INSERT INTO course_hole VALUES (1,8);
INSERT INTO course_hole VALUES (1,9);
INSERT INTO course_hole VALUES (1,10);
INSERT INTO course_hole VALUES (1,11);
INSERT INTO course_hole VALUES (1,12);
INSERT INTO course_hole VALUES (1,13);
INSERT INTO course_hole VALUES (1,14);
INSERT INTO course_hole VALUES (1,15);
INSERT INTO course_hole VALUES (1,16);
INSERT INTO course_hole VALUES (1,17);
INSERT INTO course_hole VALUES (1,18);
								
INSERT INTO hole_pin VALUES(1, 1); INSERT INTO hole_pin VALUES(10, 19); 
INSERT INTO hole_pin VALUES(1, 2); INSERT INTO hole_pin VALUES(10, 20); 
INSERT INTO hole_pin VALUES(2, 3); INSERT INTO hole_pin VALUES(11, 21); 
INSERT INTO hole_pin VALUES(2, 4); INSERT INTO hole_pin VALUES(11, 22); 
INSERT INTO hole_pin VALUES(3, 5); INSERT INTO hole_pin VALUES(12, 23); 
INSERT INTO hole_pin VALUES(3, 6); INSERT INTO hole_pin VALUES(12, 24); 
INSERT INTO hole_pin VALUES(4, 7); INSERT INTO hole_pin VALUES(13, 25); 
INSERT INTO hole_pin VALUES(4, 8); INSERT INTO hole_pin VALUES(13, 26); 
INSERT INTO hole_pin VALUES(5, 9); INSERT INTO hole_pin VALUES(14, 27); 
INSERT INTO hole_pin VALUES(5, 10); INSERT INTO hole_pin VALUES(14, 28); 
INSERT INTO hole_pin VALUES(6, 11); INSERT INTO hole_pin VALUES(15, 29); 
INSERT INTO hole_pin VALUES(6, 12); INSERT INTO hole_pin VALUES(15, 30); 
INSERT INTO hole_pin VALUES(7, 13); INSERT INTO hole_pin VALUES(16, 31); 
INSERT INTO hole_pin VALUES(7, 14); INSERT INTO hole_pin VALUES(16, 32); 
INSERT INTO hole_pin VALUES(8, 15); INSERT INTO hole_pin VALUES(17, 33); 
INSERT INTO hole_pin VALUES(8, 16); INSERT INTO hole_pin VALUES(17, 34); 
INSERT INTO hole_pin VALUES(9, 17); INSERT INTO hole_pin VALUES(18, 35); 
INSERT INTO hole_pin VALUES(9, 18); INSERT INTO hole_pin VALUES(18, 36); 











