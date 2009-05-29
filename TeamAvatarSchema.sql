DROP TABLE IF EXISTS teams ;
CREATE TABLE teams (
	id		int	not null auto_increment,
	name	varchar(100) not null,
	primary key (id)
);

DROP TABLE IF EXISTS offices ;
CREATE TABLE offices (
	id			int	not null auto_increment,
	team_id		int,
	name		varchar(100)	not	null,
	primary	key	(id),
	FOREIGN KEY (team_id) REFERENCES teams(id)
);

DROP TABLE IF EXISTS members ;
CREATE TABLE members (
	id		int	not null auto_increment,
	firstName	varchar(100)	character set utf8 not null,
	lastName	varchar(100)	character set utf8 not null,
	email		varchar(200)	not null,
	office_id	int	not null,
	photo_url	varchar(200),
	position	ENUM('team_leader', 'permanent', 'phd_student', 'intern', 'engineer')	not	null,
	mentor_id	int,
	primary	key	(id),
	foreign key (office_id) references offices(id),
	foreign key (mentor_id) references members(id)
);