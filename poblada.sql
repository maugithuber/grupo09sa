--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.7
-- Dumped by pg_dump version 9.5.7

-- Started on 2017-06-26 04:06:39 BOT

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

SET search_path = public, pg_catalog;

--
-- TOC entry 2249 (class 0 OID 17235)
-- Dependencies: 181
-- Data for Name: alumno; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO alumno VALUES (1, 'Mauricio', 'Ballesteros', 3430670, '1995-04-13', '2017-06-26', true);
INSERT INTO alumno VALUES (2, 'Alejandro', 'Alderete', 77015022, '1995-04-07', '2017-06-26', true);
INSERT INTO alumno VALUES (3, 'Lorenza', 'Ruiz', 3430615, '1994-06-15', '2017-06-26', true);


--
-- TOC entry 2273 (class 0 OID 0)
-- Dependencies: 193
-- Name: alumno_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('alumno_id_seq', 3, true);


--
-- TOC entry 2257 (class 0 OID 17265)
-- Dependencies: 189
-- Data for Name: curso; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO curso VALUES (2, 'Oratoria I', 'Curso de Oratoria', true);
INSERT INTO curso VALUES (3, 'Psicologia I', 'Curso de Psicologia', true);
INSERT INTO curso VALUES (5, 'Meditacion', 'Curso de Meditacion', true);
INSERT INTO curso VALUES (4, 'Karate', 'Curso de Karate', true);
INSERT INTO curso VALUES (6, 'Filosofia II', 'Curso de Filosofia', true);
INSERT INTO curso VALUES (7, 'Psicologia II', 'Curso de Psicologia', true);
INSERT INTO curso VALUES (8, 'Karate', 'Curso de Karate', true);
INSERT INTO curso VALUES (1, 'Filosofia I', 'Curso de Filosofia', true);
INSERT INTO curso VALUES (9, 'Mantra', 'Curso de Mantra', true);


--
-- TOC entry 2253 (class 0 OID 17251)
-- Dependencies: 185
-- Data for Name: grupo; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO grupo VALUES (1, 'Filosofia I - SA', 1);
INSERT INTO grupo VALUES (2, 'Filosofia I - SB', 1);
INSERT INTO grupo VALUES (3, 'Oratoria I - SB', 2);
INSERT INTO grupo VALUES (4, 'Oratoria I - SA', 2);
INSERT INTO grupo VALUES (5, 'Meditacion - SZ', 5);
INSERT INTO grupo VALUES (6, 'Oratoria I - SC', 2);
INSERT INTO grupo VALUES (7, 'Meditacion I - SX', 5);


--
-- TOC entry 2251 (class 0 OID 17243)
-- Dependencies: 183
-- Data for Name: kardex; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO kardex VALUES (1, NULL, 6, 2017, 1, 1);
INSERT INTO kardex VALUES (3, NULL, 6, 2017, 2, 3);
INSERT INTO kardex VALUES (2, 'A', 6, 2017, 2, 1);
INSERT INTO kardex VALUES (4, 'A', 3, 2017, 2, 5);


--
-- TOC entry 2252 (class 0 OID 17247)
-- Dependencies: 184
-- Data for Name: asistencia; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO asistencia VALUES (1, 'P', '2017-06-10', 1);
INSERT INTO asistencia VALUES (2, 'P', '2017-06-17', 1);
INSERT INTO asistencia VALUES (3, 'P', '2017-06-24', 1);
INSERT INTO asistencia VALUES (5, 'P', '2017-06-17', 2);
INSERT INTO asistencia VALUES (6, 'A', '2017-06-24', 2);
INSERT INTO asistencia VALUES (4, 'A', '2017-06-10', 2);


--
-- TOC entry 2274 (class 0 OID 0)
-- Dependencies: 196
-- Name: asistencia_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('asistencia_id_seq', 6, true);


--
-- TOC entry 2260 (class 0 OID 17275)
-- Dependencies: 192
-- Data for Name: aula; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO aula VALUES (1, 'Kairos', 40);
INSERT INTO aula VALUES (2, 'Zeus', 50);


--
-- TOC entry 2275 (class 0 OID 0)
-- Dependencies: 200
-- Name: aula_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('aula_id_seq', 2, true);


--
-- TOC entry 2254 (class 0 OID 17255)
-- Dependencies: 186
-- Data for Name: boleta_inscripcion; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO boleta_inscripcion VALUES (1, '2015-04-15', 1);
INSERT INTO boleta_inscripcion VALUES (2, '2016-04-18', 2);
INSERT INTO boleta_inscripcion VALUES (3, '2017-04-12', 2);


--
-- TOC entry 2255 (class 0 OID 17259)
-- Dependencies: 187
-- Data for Name: boleta_grupo; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO boleta_grupo VALUES (1, 1);
INSERT INTO boleta_grupo VALUES (2, 1);
INSERT INTO boleta_grupo VALUES (2, 3);
INSERT INTO boleta_grupo VALUES (3, 5);


--
-- TOC entry 2276 (class 0 OID 0)
-- Dependencies: 198
-- Name: boleta_inscripcion_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('boleta_inscripcion_id_seq', 3, true);


--
-- TOC entry 2277 (class 0 OID 0)
-- Dependencies: 199
-- Name: curso_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('curso_id_seq', 9, true);


--
-- TOC entry 2278 (class 0 OID 0)
-- Dependencies: 197
-- Name: grupo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('grupo_id_seq', 7, true);


--
-- TOC entry 2259 (class 0 OID 17272)
-- Dependencies: 191
-- Data for Name: horario; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO horario VALUES (1, 1, 'Sabado', '13:00', '14:30', 1);
INSERT INTO horario VALUES (2, 1, 'Sabado', '14:30', '16:30', 1);
INSERT INTO horario VALUES (3, 1, 'Sabado', '16:30', '18:00', 1);
INSERT INTO horario VALUES (4, 1, 'Sabado', '18:00', '19:30', 1);
INSERT INTO horario VALUES (5, 1, 'Sabado', '13:30', '15:00', 2);
INSERT INTO horario VALUES (6, 1, 'Domingo', '10:30', '12:00', 2);
INSERT INTO horario VALUES (7, 1, 'Domingo', '12:30', '14:00', 2);
INSERT INTO horario VALUES (1, 2, 'Domingo', '11:30', '13:00', 2);


--
-- TOC entry 2250 (class 0 OID 17239)
-- Dependencies: 182
-- Data for Name: profesor; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO profesor VALUES (1, 'Evans', 'Balcazar', 72172154, '1999-05-15', true);
INSERT INTO profesor VALUES (2, 'Josue', 'Veizaga', 72185415, '1991-04-30', true);


--
-- TOC entry 2256 (class 0 OID 17262)
-- Dependencies: 188
-- Data for Name: imparte; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO imparte VALUES (1, 1, '2017-06-26');
INSERT INTO imparte VALUES (1, 2, '2017-06-26');
INSERT INTO imparte VALUES (1, 3, '2017-06-26');
INSERT INTO imparte VALUES (2, 4, '2017-06-26');
INSERT INTO imparte VALUES (2, 5, '2017-06-26');
INSERT INTO imparte VALUES (2, 6, '2017-06-26');
INSERT INTO imparte VALUES (2, 7, '2017-06-26');


--
-- TOC entry 2279 (class 0 OID 0)
-- Dependencies: 195
-- Name: kardex_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('kardex_id_seq', 5, true);


--
-- TOC entry 2258 (class 0 OID 17269)
-- Dependencies: 190
-- Data for Name: prerequisitos; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO prerequisitos VALUES (6, 1);
INSERT INTO prerequisitos VALUES (7, 3);


--
-- TOC entry 2280 (class 0 OID 0)
-- Dependencies: 194
-- Name: profesor_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('profesor_id_seq', 2, true);


-- Completed on 2017-06-26 04:06:39 BOT

--
-- PostgreSQL database dump complete
--

