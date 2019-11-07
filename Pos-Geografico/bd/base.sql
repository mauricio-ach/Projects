create table usuario(
	id_usuario serial not null,
	nombre_usuario text not null,
	contrasenia text not null,
	correo text not null,
	rol int not null,
	constraint "correo_unique" unique(correo),
	constraint "usuario_pk" primary key(id_usuario)
	);

create table tema(
	tipo_tema text not null,
	color text not null,
	id_usuario int not null,
	constraint "color_unique"  unique(color),
	constraint "tema_pk" primary key(tipo_tema)
	);

create table marcador(
	num_marcador serial not null,
	descripcion text not null,
	datos_utiles text,
	longitud double precision not null,
	latitud double precision not null,
	tipo_tema text not null,
	constraint "marcador_pk" primary key(num_marcador)
	);

create table comentario(
	num_comentario serial not null,
	contenido text not null,
	likes int not null,
	dislikes int not null,
	num_marcador int not null,
	id_usuario int not null,
	constraint "comentario_pk" primary key(num_comentario)
	);

alter table tema add constraint "tema_fk" foreign key(id_usuario) references usuario(id_usuario)
on delete cascade on update cascade;

alter table marcador add constraint "marcador_fk" foreign key (tipo_tema) references tema(tipo_tema)
on delete cascade on update cascade;

alter table comentario add constraint "comentario_fk_marcador" foreign key(num_marcador) references marcador(num_marcador)
on delete cascade on update cascade;

alter table comentario add constraint "comentario_fk_usuario" foreign key(id_usuario) references usuario(id_usuario)
on delete cascade on update cascade;
