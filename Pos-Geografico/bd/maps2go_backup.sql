--
-- PostgreSQL database dump
--

-- Dumped from database version 10.7 (Ubuntu 10.7-1.pgdg18.04+1)
-- Dumped by pg_dump version 10.7 (Ubuntu 10.7-1.pgdg18.04+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: maps2go; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE maps2go WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'es_MX.UTF-8' LC_CTYPE = 'es_MX.UTF-8';


ALTER DATABASE maps2go OWNER TO postgres;

\connect maps2go

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner:
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner:
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: comentario; Type: TABLE; Schema: public; Owner: maave
--

CREATE TABLE public.comentario (
    num_comentario integer NOT NULL,
    contenido text NOT NULL,
    likes integer NOT NULL,
    dislikes integer NOT NULL,
    num_marcador integer NOT NULL,
    id_usuario integer NOT NULL
);


ALTER TABLE public.comentario OWNER TO maave;

--
-- Name: comentario_num_comentario_seq; Type: SEQUENCE; Schema: public; Owner: maave
--

CREATE SEQUENCE public.comentario_num_comentario_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.comentario_num_comentario_seq OWNER TO maave;

--
-- Name: comentario_num_comentario_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: maave
--

ALTER SEQUENCE public.comentario_num_comentario_seq OWNED BY public.comentario.num_comentario;


--
-- Name: marcador; Type: TABLE; Schema: public; Owner: maave
--

CREATE TABLE public.marcador (
    num_marcador integer NOT NULL,
    descripcion text NOT NULL,
    datos_utiles text,
    longitud double precision NOT NULL,
    latitud double precision NOT NULL,
    tipo_tema text NOT NULL
);


ALTER TABLE public.marcador OWNER TO maave;

--
-- Name: marcador_num_marcador_seq; Type: SEQUENCE; Schema: public; Owner: maave
--

CREATE SEQUENCE public.marcador_num_marcador_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.marcador_num_marcador_seq OWNER TO maave;

--
-- Name: marcador_num_marcador_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: maave
--

ALTER SEQUENCE public.marcador_num_marcador_seq OWNED BY public.marcador.num_marcador;


--
-- Name: tema; Type: TABLE; Schema: public; Owner: maave
--

CREATE TABLE public.tema (
    tipo_tema text NOT NULL,
    color text NOT NULL,
    id_usuario integer NOT NULL
);


ALTER TABLE public.tema OWNER TO maave;

--
-- Name: usuario; Type: TABLE; Schema: public; Owner: maave
--

CREATE TABLE public.usuario (
    id_usuario integer NOT NULL,
    nombre_usuario text NOT NULL,
    contrasenia text NOT NULL,
    correo text NOT NULL,
    rol integer NOT NULL
);


ALTER TABLE public.usuario OWNER TO maave;

--
-- Name: usuario_id_usuario_seq; Type: SEQUENCE; Schema: public; Owner: maave
--

CREATE SEQUENCE public.usuario_id_usuario_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.usuario_id_usuario_seq OWNER TO maave;

--
-- Name: usuario_id_usuario_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: maave
--

ALTER SEQUENCE public.usuario_id_usuario_seq OWNED BY public.usuario.id_usuario;


--
-- Name: comentario num_comentario; Type: DEFAULT; Schema: public; Owner: maave
--

ALTER TABLE ONLY public.comentario ALTER COLUMN num_comentario SET DEFAULT nextval('public.comentario_num_comentario_seq'::regclass);


--
-- Name: marcador num_marcador; Type: DEFAULT; Schema: public; Owner: maave
--

ALTER TABLE ONLY public.marcador ALTER COLUMN num_marcador SET DEFAULT nextval('public.marcador_num_marcador_seq'::regclass);


--
-- Name: usuario id_usuario; Type: DEFAULT; Schema: public; Owner: maave
--

ALTER TABLE ONLY public.usuario ALTER COLUMN id_usuario SET DEFAULT nextval('public.usuario_id_usuario_seq'::regclass);


--
-- Data for Name: comentario; Type: TABLE DATA; Schema: public; Owner: maave
--

COPY public.comentario (num_comentario, contenido, likes, dislikes, num_marcador, id_usuario) FROM stdin;
2	1er comentario	0	0	1	7
3	1er comentario	0	0	1	7
4	otro comentario	0	0	1	8
5	otro comentario	0	0	1	8
1	asdfghjklñ{	0	0	1	7
\.


--
-- Data for Name: marcador; Type: TABLE DATA; Schema: public; Owner: maave
--

COPY public.marcador (num_marcador, descripcion, datos_utiles, longitud, latitud, tipo_tema) FROM stdin;
1	Alto consumo	Niveles de enfermedad altos	-105.437918999999994	25.4279130000000002	Tabaco
2	Lugar en donde venden tabaco a menores de edad	Niños de 13 años fuman	-99.0219009999999997	19.3365609999999997	Tabaco
3	La adicción de tabaco en este lugar es muy grande	-99.1798969999999969	19.3712680000000006	Tabaco
4	En este lugar se consume alcohol desde pequeños Causa: ausencia de padres	-99.1778419999999983	19.3748460000000016	Alcohol
5 Zona con el más alto nivel de accidentes por el alcohol Causa: manejan con estado de ebriedad -98.600071 -98.600071 Alcohol
6 Lugar en donde se vende la mayor cantidad de alcohol adulterado Causa: ganar dinero fácil -109.234836 27.585997 Alcohol
7 Prácticas de aborto clandestino No legalización del aborto -99.962375 26.096051 Aborto
8 Lugar con mayor índice de aborto Causa: desinformación -103.741672 24.266790 Aborto
9 Abortos en menores de edad por violación Niñas abortan porque sufrieron una violación normalmente son niñas de 15 años -103.346164 21.759289 Aborto
10 Más innovaciones en la tecnología Cuentan con más personas preparadas -98.995579 19.745811 Tecnología
11 La tecnología es vista como algo malo personas no aceptan la introducción de innovaciones -95.084446 16.572805 Tecnología
12 Hay muchos proyectos para la tecnología pero no hay la economía suficiente Causa: pobreza -100.929172 20.365015 Tecnología
13 Lugar con mayor tasa de bullying Lugar: escuelas públicas y privadas -103.170383 20.365015 Bullying
14 El bullying se hizo muy famoso  Se práctica desde casa en este lugar se practica en casa -99.124518 22.090238 Bullying
15 Lugar donde casi no existe el bullying Causa: mucho respeto y buena educación -98.685065 20.328677 Bullying
16 El embarazo no deseado se intensificó en esta zona Causa:poca información -92.444830 17.376346 Embarazo
17 Niñas de 12 años se embarazan Causa: acostumbran a casarse desde pequeñas -98.465338 16.661998 Embarazo
18 En esta zona hay un alto índice de embarazos no deseados Muchas chicas no abortan por falta de dinero -89.939947 18.755009 Embarazo
19 Jóvenes comienzan drogadicción desde temprana edad El consumo de drogas aumentó en un 40% -96.575690 19.709329 Drogradicción
20 A lo largo de los años en este lugar se han detectado más drogas Se han encontrado drogas muy extrañas -107.825690 24.990556 Drogradicción
21 Se conoce que en esta zona han habido muchas muertes por el alto consumo de drogas -112.264166 26.965669 Drogadicción
22 Zona en donde se encuentran las universidades con mayor demanda -99.080572 19.295089 Universidades
23 Universidades con gran índice de suicidios Causas:estrés depresión soledad -98.333502 19.419472 Universidades
24 Universidades con falta de recursos para la realización de prácticas La mayor ausa es la falta de presupuesto -96.224127 18.962939 Universidades
25 La mayoría de la población comienza a usar las redes sociales desde los 12 años Por la popularidad de dichas redes -100.179205 20.246238 Redes Sociales
26 El vínculo en lad redes sociales genera noviazgos Esto puede ser peligroso ya que normalmente secuestradores se aprovechan -103.826666 25.308790 Redes Sociales
27 Existe una constante extorsión en las redes sociales Gracias a la falta de cuidado -98.113776 17.711555 Redes Sociales
28 La violencia familiar es un problema alarmante en la zona Mayor maltrato a la mamá -104.134283 28.056970 Violencia
29 Un problema muy grave en los noviazgos es la violencia Mujeres se dejan manipular por su novio -106.419440 25.863689 Violencia
30 Los alumnos de las escuelas hacen uso de la violencia para todo desde pequeños los enseñan a pelear
31 Zona con tasa alta en cazar animales razones: demanda de sus pieles o extravagancia -87.215338 21.191599 Animales
32 Los animales son tratados adecuadamente dignos de dicho trato -102.728033 21.968027 Animales
33 En este lugar los animales son más populares como mascotas La mayoria de personas adopta perros o gatos -104.617682 24.871005 Animales
34 Choles Viven en zona montañosa con clima cálido húmedo, cerca de ríos caudalosos como el río Tulijá -92.447727 16.783288 Tribus
35 Hichol Debido al aislamiento y resistencia a la evangelización que deliberadamente hay mantienen muchos de sus trazos culturales originales -103.697727 20.323811 Tribus
36 Mayas Los mayas fueron una cultura avanzada que existió antes de la llegada de los españoles se calcula que esta cultura inicio de 1000 años AC hasta 320 DC -90.118625 19.082670 Tribus
37 Dia de Muertos Se celebra el 1 y 2 de noviembre -101.632297 19.082670 Tradiciones
38 Danza de los voladores comienza con un baile y posteriormente cinco participantes se suben a un poste de 30 metros -97.940891 18.583561 Tradiciones
39 La Guelaguetza Esta es una celebración mixta en la que las muestras de danzas tradicionales se mezclan con la música los trajes típicos y la gastronomía típica mexicana -96.622532 16.614920 Tradiciones
40 Los conciertos de música que se imparten en la zona son muy asistidos Hay alto índice de aceptación -98.685065 19.170611 Musica
41 La música más escuchada es del género de banda Les encanta la banda -108.133307 26.219043 Musica
42 Los habitantes de la zona escuchan la música para realizar cualquier actividad en su vida diaria Los relaja -102.508307 20.081231 Musica
43 Gran parte de la población realiza deportes, en especial los jóvenes Son impulsados a practicar deporte  -99.124518 21.559899 Deporte
44 Los deportes que más sobresalen son los de contacto Son expertos en esta área -111.604986 29.864005 Deporte
45 La práctica de deportes es muy escasa Falta de impulso deportivo -103.299322 20.904507 Deporte
46 Los grandes avances tecnológicos en los celulares se deben gracias a la ciencia Gracias a ella podemos tener todo en un sólo aparato -103.917454 19.952483 Ciencia
47 La ciencia de la comunicación sirve para poder dispersar información de forma más rápida y directa En esta zona es muy popular -98.380344 20.981745 Ciencia
48 Gracias a los avances en la ciencia se han creado nuevas medicinas para combatir enfermedades Muchas de ellas son raras -90.074680 19.911170 Ciencia
49 La nueva preocupación de la población es lograr que la contaminación disminuya Por eso están cambiando hábitos -98.512180 19.414578 Contaminacion
50 Los nuevos lujos que se da la gente hace que la contaminación vaya en aumento En este lugar es el mayor con contaminantes -99.171360 25.581881 Contaminacion
51 La contaminación es un problema difícil de erradicar Se necesia de mucha voluntad y educación -103.917454 24.466944 Contaminacion
52 Se registró un alto índice de asisinatos de mujere El procentaje fue de 20% más que otros años -105.894993 28.709662 Feminicidios
53 En este lugar casi no se han presentado casos de feminicidio Las mujeres son muy respetadas -100.665500 19.993785 Feminicidios
54 Nueva moda que surgió hace 2 años Comenzaron a morir muchas mujeres sospechosamente -97.853000 25.819468 Feminicidios
55 La seguridad en todas las calles es excelente, se pude estar tranquilo  cualquier momento -101.061008 20.961228 Seguridad
56 La seguridad dentro de los lugares donde se llevan a cabo las fiestas es muy mala -99.610813 19.144954 Seguridad
57 La falta de seguridad dentro de las escuelas ocasiona muchos problemas -94.337375 17.727542 Seguridad
58 El índice de pobreza es elevado en ésta zona No se cuentan con los trabajos necesarios -92.840338 17.334401 Pobreza
59 Un problema que preocupa a toda la población es la pobreza  Falta de trabajos y dinero induce a convertirse en ladrón -96.136236 17.627811 Pobreza
60 La pobreza ha ido en aumento en los últimos años Causa: el desempleo -92.532721 15.606654 Pobreza
\.


--
-- Data for Name: tema; Type: TABLE DATA; Schema: public; Owner: maave
--

COPY public.tema (tipo_tema, color, id_usuario) FROM stdin;
Tabaco cf2929 2
Alcohol be18c7 2
Aborto 3304cc 2
Tecnología 63c1ed 2
Bullying 0fa1a6 2
Embarazo 0eeb6e 2
Drogradicción 236b06 4
Universidades b0f04f 4
Redes Sociales f5f53b 4
Violencia b5770d 4
Animales f09e57 4
Tribus e83407 4
Tradiciones f0a998 4
Musica 731b05 8
Deportes b88d8d 8
Ciencia 252b42 8
Contaminacion 0b7818 8
Feminicidios 5c125c 6
Seguridad 0d090c 6
Pobreza 77969c 6


\.


--
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: maave
--

COPY public.usuario (id_usuario, nombre_usuario, contrasenia, correo, rol) FROM stdin;
1	maave	maave	maave@gmail.com	1
2	Vale	contraseña	vale@gmail.com	2
3	Vale12	contraseña	vale.garcia.landa@gmail.com	3
4	Angela	iruXe009QUP	angelajanin@gmail.com	2
5	Ana	123456	ana@gmail.com	3
6	Luis	56789	luis@gmail.com	2
7	mauricio666	contraseña	maudrummer36@gmail.com	3
8 Erick 2018 erick@gmail.com 2
\.


--
-- Name: comentario_num_comentario_seq; Type: SEQUENCE SET; Schema: public; Owner: maave
--

SELECT pg_catalog.setval('public.comentario_num_comentario_seq', 6, true);


--
-- Name: marcador_num_marcador_seq; Type: SEQUENCE SET; Schema: public; Owner: maave
--

SELECT pg_catalog.setval('public.marcador_num_marcador_seq', 4, true);


--
-- Name: usuario_id_usuario_seq; Type: SEQUENCE SET; Schema: public; Owner: maave
--

SELECT pg_catalog.setval('public.usuario_id_usuario_seq', 13, true);


--
-- Name: tema color_unique; Type: CONSTRAINT; Schema: public; Owner: maave
--

ALTER TABLE ONLY public.tema
    ADD CONSTRAINT color_unique UNIQUE (color);


--
-- Name: comentario comentario_pk; Type: CONSTRAINT; Schema: public; Owner: maave
--

ALTER TABLE ONLY public.comentario
    ADD CONSTRAINT comentario_pk PRIMARY KEY (num_comentario);


--
-- Name: usuario correo_unique; Type: CONSTRAINT; Schema: public; Owner: maave
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT correo_unique UNIQUE (correo);


--
-- Name: marcador marcador_pk; Type: CONSTRAINT; Schema: public; Owner: maave
--

ALTER TABLE ONLY public.marcador
    ADD CONSTRAINT marcador_pk PRIMARY KEY (num_marcador);


--
-- Name: tema tema_pk; Type: CONSTRAINT; Schema: public; Owner: maave
--

ALTER TABLE ONLY public.tema
    ADD CONSTRAINT tema_pk PRIMARY KEY (tipo_tema);


--
-- Name: usuario usuario_pk; Type: CONSTRAINT; Schema: public; Owner: maave
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_pk PRIMARY KEY (id_usuario);


--
-- Name: comentario comentario_fk_marcador; Type: FK CONSTRAINT; Schema: public; Owner: maave
--

ALTER TABLE ONLY public.comentario
    ADD CONSTRAINT comentario_fk_marcador FOREIGN KEY (num_marcador) REFERENCES public.marcador(num_marcador) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: comentario comentario_fk_usuario; Type: FK CONSTRAINT; Schema: public; Owner: maave
--

ALTER TABLE ONLY public.comentario
    ADD CONSTRAINT comentario_fk_usuario FOREIGN KEY (id_usuario) REFERENCES public.usuario(id_usuario) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: marcador marcador_fk; Type: FK CONSTRAINT; Schema: public; Owner: maave
--

ALTER TABLE ONLY public.marcador
    ADD CONSTRAINT marcador_fk FOREIGN KEY (tipo_tema) REFERENCES public.tema(tipo_tema) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: tema tema_fk; Type: FK CONSTRAINT; Schema: public; Owner: maave
--

ALTER TABLE ONLY public.tema
    ADD CONSTRAINT tema_fk FOREIGN KEY (id_usuario) REFERENCES public.usuario(id_usuario) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- PostgreSQL database dump complete
--
