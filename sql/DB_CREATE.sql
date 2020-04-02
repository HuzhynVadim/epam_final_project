--
-- PostgreSQL database dump
--

-- Dumped from database version 12.2
-- Dumped by pg_dump version 12.2

-- Started on 2020-03-24 23:21:03

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 7 (class 2615 OID 26010)
-- Name: railway_system; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA railway_system;


ALTER SCHEMA railway_system OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 26064)
-- Name: CarType_carTypeId_seq; Type: SEQUENCE; Schema: railway_system; Owner: postgres
--

CREATE SEQUENCE railway_system."CarType_carTypeId_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;


ALTER TABLE railway_system."CarType_carTypeId_seq" OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 26054)
-- Name: Cars_carId_seq; Type: SEQUENCE; Schema: railway_system; Owner: postgres
--

CREATE SEQUENCE railway_system."Cars_carId_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;


ALTER TABLE railway_system."Cars_carId_seq" OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 26066)
-- Name: Order_orderId_seq; Type: SEQUENCE; Schema: railway_system; Owner: postgres
--

CREATE SEQUENCE railway_system."Order_orderId_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;


ALTER TABLE railway_system."Order_orderId_seq" OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 26027)
-- Name: Rolle_id_seq; Type: SEQUENCE; Schema: railway_system; Owner: postgres
--

CREATE SEQUENCE railway_system."Rolle_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;


ALTER TABLE railway_system."Rolle_id_seq" OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 26036)
-- Name: Routs_id_seq; Type: SEQUENCE; Schema: railway_system; Owner: postgres
--

CREATE SEQUENCE railway_system."Routs_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;


ALTER TABLE railway_system."Routs_id_seq" OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 26045)
-- Name: StationType_id_seq; Type: SEQUENCE; Schema: railway_system; Owner: postgres
--

CREATE SEQUENCE railway_system."StationType_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;


ALTER TABLE railway_system."StationType_id_seq" OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 26029)
-- Name: Stations_id_seq; Type: SEQUENCE; Schema: railway_system; Owner: postgres
--

CREATE SEQUENCE railway_system."Stations_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;


ALTER TABLE railway_system."Stations_id_seq" OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 26015)
-- Name: Trains_id_seq; Type: SEQUENCE; Schema: railway_system; Owner: postgres
--

CREATE SEQUENCE railway_system."Trains_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;


ALTER TABLE railway_system."Trains_id_seq" OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 26047)
-- Name: Trains_id_seq1; Type: SEQUENCE; Schema: railway_system; Owner: postgres
--

CREATE SEQUENCE railway_system."Trains_id_seq1"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;


ALTER TABLE railway_system."Trains_id_seq1" OWNER TO postgres;

--
-- TOC entry 208 (class 1259 OID 26017)
-- Name: Users_id_seq; Type: SEQUENCE; Schema: railway_system; Owner: postgres
--

CREATE SEQUENCE railway_system."Users_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;


ALTER TABLE railway_system."Users_id_seq" OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 26043)
-- Name: WayStation_id_seq; Type: SEQUENCE; Schema: railway_system; Owner: postgres
--

CREATE SEQUENCE railway_system."WayStation_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;


ALTER TABLE railway_system."WayStation_id_seq" OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 220 (class 1259 OID 26056)
-- Name: car; Type: TABLE; Schema: railway_system; Owner: postgres
--

CREATE TABLE railway_system.car (
                                    "carId" character varying(64) NOT NULL,
                                    "carType" character varying(64) NOT NULL,
                                    "carNumber" character varying(64) NOT NULL,
                                    "trainId" character varying(64) NOT NULL,
                                    seats character varying(64) NOT NULL,
                                    price numeric NOT NULL
);


ALTER TABLE railway_system.car OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 26011)
-- Name: invoices_id_seq; Type: SEQUENCE; Schema: railway_system; Owner: postgres
--

CREATE SEQUENCE railway_system.invoices_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;


ALTER TABLE railway_system.invoices_id_seq OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 26068)
-- Name: order; Type: TABLE; Schema: railway_system; Owner: postgres
--

CREATE TABLE railway_system."order" (
                                        "orderId" character varying(64) NOT NULL,
                                        "trainNumber" character varying(64) NOT NULL,
                                        "carNumber" character varying(64) NOT NULL,
                                        "carType" character varying(64) NOT NULL,
                                        price numeric NOT NULL,
                                        "arrivalDate" timestamp with time zone NOT NULL,
                                        "dispatchDate" timestamp with time zone NOT NULL,
                                        "userId" character varying(64),
                                        "orderDate" timestamp with time zone NOT NULL,
                                        "orderStatus" character varying(64)
);


ALTER TABLE railway_system."order" OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 26013)
-- Name: prices_id_seq; Type: SEQUENCE; Schema: railway_system; Owner: postgres
--

CREATE SEQUENCE railway_system.prices_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;


ALTER TABLE railway_system.prices_id_seq OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 26038)
-- Name: rout; Type: TABLE; Schema: railway_system; Owner: postgres
--

CREATE TABLE railway_system.rout (
                                     "routsId" character varying(64) NOT NULL,
                                     "routDispatchDate" timestamp with time zone NOT NULL,
                                     "routArrivalDate" timestamp with time zone NOT NULL,
                                     "trainId" character varying(64) NOT NULL
);


ALTER TABLE railway_system.rout OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 26076)
-- Name: rout_to_station_mapping; Type: TABLE; Schema: railway_system; Owner: postgres
--

CREATE TABLE railway_system.rout_to_station_mapping (
                                                        "stationId" character varying(64) NOT NULL,
                                                        "routsId" character varying(64) NOT NULL,
                                                        "stationArrivalDate" timestamp with time zone NOT NULL,
                                                        "stationDispatchData" timestamp with time zone NOT NULL,
                                                        "order" character varying(64) NOT NULL
);


ALTER TABLE railway_system.rout_to_station_mapping OWNER TO postgres;

--
-- TOC entry 212 (class 1259 OID 26031)
-- Name: station; Type: TABLE; Schema: railway_system; Owner: postgres
--

CREATE TABLE railway_system.station (
                                        "stationId" character varying(64) NOT NULL,
                                        station character varying(100) NOT NULL
);


ALTER TABLE railway_system.station OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 26049)
-- Name: train; Type: TABLE; Schema: railway_system; Owner: postgres
--

CREATE TABLE railway_system.train (
                                      "trainId" character varying(64) NOT NULL,
                                      "trainNumber" character varying(64) NOT NULL
);


ALTER TABLE railway_system.train OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 26019)
-- Name: user; Type: TABLE; Schema: railway_system; Owner: postgres
--

CREATE TABLE railway_system."user" (
                                       "userId" character varying(64) NOT NULL,
                                       email character varying(255) NOT NULL,
                                       password character varying(255) NOT NULL,
                                       "firstName" character varying(25) NOT NULL,
                                       "secondName" character varying(25) NOT NULL,
                                       phone character varying(25) NOT NULL,
                                       "birthDate" timestamp with time zone NOT NULL,
                                       role character varying(25) NOT NULL
);


ALTER TABLE railway_system."user" OWNER TO postgres;

--
-- TOC entry 2902 (class 0 OID 26056)
-- Dependencies: 220
-- Data for Name: car; Type: TABLE DATA; Schema: railway_system; Owner: postgres
--

COPY railway_system.car ("carId", "carType", "carNumber", "trainId", seats, price) FROM stdin;
\.


--
-- TOC entry 2905 (class 0 OID 26068)
-- Dependencies: 223
-- Data for Name: order; Type: TABLE DATA; Schema: railway_system; Owner: postgres
--

COPY railway_system."order" ("orderId", "trainNumber", "carNumber", "carType", price, "arrivalDate", "dispatchDate", "userId", "orderDate", "orderStatus") FROM stdin;
\.


--
-- TOC entry 2896 (class 0 OID 26038)
-- Dependencies: 214
-- Data for Name: rout; Type: TABLE DATA; Schema: railway_system; Owner: postgres
--

COPY railway_system.rout ("routsId", "routDispatchDate", "routArrivalDate", "trainId") FROM stdin;
\.


--
-- TOC entry 2906 (class 0 OID 26076)
-- Dependencies: 224
-- Data for Name: rout_to_station_mapping; Type: TABLE DATA; Schema: railway_system; Owner: postgres
--

COPY railway_system.rout_to_station_mapping ("stationId", "routsId", "stationArrivalDate", "stationDispatchData", "order") FROM stdin;
\.


--
-- TOC entry 2894 (class 0 OID 26031)
-- Dependencies: 212
-- Data for Name: station; Type: TABLE DATA; Schema: railway_system; Owner: postgres
--

COPY railway_system.station ("stationId", station) FROM stdin;
\.


--
-- TOC entry 2900 (class 0 OID 26049)
-- Dependencies: 218
-- Data for Name: train; Type: TABLE DATA; Schema: railway_system; Owner: postgres
--

COPY railway_system.train ("trainId", "trainNumber") FROM stdin;
\.


--
-- TOC entry 2891 (class 0 OID 26019)
-- Dependencies: 209
-- Data for Name: user; Type: TABLE DATA; Schema: railway_system; Owner: postgres
--

COPY railway_system."user" ("userId", email, password, "firstName", "secondName", phone, "birthDate", role) FROM stdin;
\.


--
-- TOC entry 2912 (class 0 OID 0)
-- Dependencies: 221
-- Name: CarType_carTypeId_seq; Type: SEQUENCE SET; Schema: railway_system; Owner: postgres
--

SELECT pg_catalog.setval('railway_system."CarType_carTypeId_seq"', 1, false);


--
-- TOC entry 2913 (class 0 OID 0)
-- Dependencies: 219
-- Name: Cars_carId_seq; Type: SEQUENCE SET; Schema: railway_system; Owner: postgres
--

SELECT pg_catalog.setval('railway_system."Cars_carId_seq"', 1, false);


--
-- TOC entry 2914 (class 0 OID 0)
-- Dependencies: 222
-- Name: Order_orderId_seq; Type: SEQUENCE SET; Schema: railway_system; Owner: postgres
--

SELECT pg_catalog.setval('railway_system."Order_orderId_seq"', 1, false);


--
-- TOC entry 2915 (class 0 OID 0)
-- Dependencies: 210
-- Name: Rolle_id_seq; Type: SEQUENCE SET; Schema: railway_system; Owner: postgres
--

SELECT pg_catalog.setval('railway_system."Rolle_id_seq"', 1, false);


--
-- TOC entry 2916 (class 0 OID 0)
-- Dependencies: 213
-- Name: Routs_id_seq; Type: SEQUENCE SET; Schema: railway_system; Owner: postgres
--

SELECT pg_catalog.setval('railway_system."Routs_id_seq"', 1, false);


--
-- TOC entry 2917 (class 0 OID 0)
-- Dependencies: 216
-- Name: StationType_id_seq; Type: SEQUENCE SET; Schema: railway_system; Owner: postgres
--

SELECT pg_catalog.setval('railway_system."StationType_id_seq"', 1, false);


--
-- TOC entry 2918 (class 0 OID 0)
-- Dependencies: 211
-- Name: Stations_id_seq; Type: SEQUENCE SET; Schema: railway_system; Owner: postgres
--

SELECT pg_catalog.setval('railway_system."Stations_id_seq"', 1, false);


--
-- TOC entry 2919 (class 0 OID 0)
-- Dependencies: 207
-- Name: Trains_id_seq; Type: SEQUENCE SET; Schema: railway_system; Owner: postgres
--

SELECT pg_catalog.setval('railway_system."Trains_id_seq"', 1, false);


--
-- TOC entry 2920 (class 0 OID 0)
-- Dependencies: 217
-- Name: Trains_id_seq1; Type: SEQUENCE SET; Schema: railway_system; Owner: postgres
--

SELECT pg_catalog.setval('railway_system."Trains_id_seq1"', 1, false);


--
-- TOC entry 2921 (class 0 OID 0)
-- Dependencies: 208
-- Name: Users_id_seq; Type: SEQUENCE SET; Schema: railway_system; Owner: postgres
--

SELECT pg_catalog.setval('railway_system."Users_id_seq"', 1, false);


--
-- TOC entry 2922 (class 0 OID 0)
-- Dependencies: 215
-- Name: WayStation_id_seq; Type: SEQUENCE SET; Schema: railway_system; Owner: postgres
--

SELECT pg_catalog.setval('railway_system."WayStation_id_seq"', 1, false);


--
-- TOC entry 2923 (class 0 OID 0)
-- Dependencies: 205
-- Name: invoices_id_seq; Type: SEQUENCE SET; Schema: railway_system; Owner: postgres
--

SELECT pg_catalog.setval('railway_system.invoices_id_seq', 1, false);


--
-- TOC entry 2924 (class 0 OID 0)
-- Dependencies: 206
-- Name: prices_id_seq; Type: SEQUENCE SET; Schema: railway_system; Owner: postgres
--

SELECT pg_catalog.setval('railway_system.prices_id_seq', 1, false);


--
-- TOC entry 2750 (class 2606 OID 26063)
-- Name: car Cars_pk; Type: CONSTRAINT; Schema: railway_system; Owner: postgres
--

ALTER TABLE ONLY railway_system.car
    ADD CONSTRAINT "Cars_pk" PRIMARY KEY ("carId");


--
-- TOC entry 2752 (class 2606 OID 26075)
-- Name: order Order_pk; Type: CONSTRAINT; Schema: railway_system; Owner: postgres
--

ALTER TABLE ONLY railway_system."order"
    ADD CONSTRAINT "Order_pk" PRIMARY KEY ("orderId");


--
-- TOC entry 2746 (class 2606 OID 26042)
-- Name: rout Routs_pkey; Type: CONSTRAINT; Schema: railway_system; Owner: postgres
--

ALTER TABLE ONLY railway_system.rout
    ADD CONSTRAINT "Routs_pkey" PRIMARY KEY ("routsId");


--
-- TOC entry 2744 (class 2606 OID 26035)
-- Name: station Stations_pkey; Type: CONSTRAINT; Schema: railway_system; Owner: postgres
--

ALTER TABLE ONLY railway_system.station
    ADD CONSTRAINT "Stations_pkey" PRIMARY KEY ("stationId");


--
-- TOC entry 2748 (class 2606 OID 26053)
-- Name: train Trains_pk; Type: CONSTRAINT; Schema: railway_system; Owner: postgres
--

ALTER TABLE ONLY railway_system.train
    ADD CONSTRAINT "Trains_pk" PRIMARY KEY ("trainId");


--
-- TOC entry 2742 (class 2606 OID 26026)
-- Name: user Users_pkey; Type: CONSTRAINT; Schema: railway_system; Owner: postgres
--

ALTER TABLE ONLY railway_system."user"
    ADD CONSTRAINT "Users_pkey" PRIMARY KEY ("userId");


--
-- TOC entry 2754 (class 2606 OID 26080)
-- Name: rout_to_station_mapping routs_s_pk; Type: CONSTRAINT; Schema: railway_system; Owner: postgres
--

ALTER TABLE ONLY railway_system.rout_to_station_mapping
    ADD CONSTRAINT routs_s_pk PRIMARY KEY ("stationId", "routsId");


--
-- TOC entry 2755 (class 2606 OID 26081)
-- Name: rout Train_Id; Type: FK CONSTRAINT; Schema: railway_system; Owner: postgres
--

ALTER TABLE ONLY railway_system.rout
    ADD CONSTRAINT "Train_Id" FOREIGN KEY ("trainId") REFERENCES railway_system.train("trainId") MATCH FULL ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2756 (class 2606 OID 26086)
-- Name: car Train_Id; Type: FK CONSTRAINT; Schema: railway_system; Owner: postgres
--

ALTER TABLE ONLY railway_system.car
    ADD CONSTRAINT "Train_Id" FOREIGN KEY ("trainId") REFERENCES railway_system.train("trainId") MATCH FULL ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2759 (class 2606 OID 26101)
-- Name: rout_to_station_mapping routs_id; Type: FK CONSTRAINT; Schema: railway_system; Owner: postgres
--

ALTER TABLE ONLY railway_system.rout_to_station_mapping
    ADD CONSTRAINT routs_id FOREIGN KEY ("routsId") REFERENCES railway_system.rout("routsId") MATCH FULL ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2758 (class 2606 OID 26096)
-- Name: rout_to_station_mapping station_id; Type: FK CONSTRAINT; Schema: railway_system; Owner: postgres
--

ALTER TABLE ONLY railway_system.rout_to_station_mapping
    ADD CONSTRAINT station_id FOREIGN KEY ("stationId") REFERENCES railway_system.station("stationId") MATCH FULL ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2757 (class 2606 OID 26091)
-- Name: order user_id; Type: FK CONSTRAINT; Schema: railway_system; Owner: postgres
--

ALTER TABLE ONLY railway_system."order"
    ADD CONSTRAINT user_id FOREIGN KEY ("userId") REFERENCES railway_system."user"("userId") MATCH FULL ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2886 (class 0 OID 26031)
-- Dependencies: 212
-- Name: station; Type: ROW SECURITY; Schema: railway_system; Owner: postgres
--

ALTER TABLE railway_system.station ENABLE ROW LEVEL SECURITY;

-- Completed on 2020-03-24 23:21:04

--
-- PostgreSQL database dump complete
--

