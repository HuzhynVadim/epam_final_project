--
-- PostgreSQL database dump
--

-- Dumped from database version 12.2
-- Dumped by pg_dump version 12.2

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
-- Name: railway_system; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA railway_system;


ALTER SCHEMA railway_system OWNER TO postgres;

--
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
-- Name: car; Type: TABLE; Schema: railway_system; Owner: postgres
--

CREATE TABLE railway_system.car (
                                    car_id character varying(64) NOT NULL,
                                    car_type character varying(64) NOT NULL,
                                    car_number character varying(64),
                                    train_id character varying(64),
                                    seats integer NOT NULL
);


ALTER TABLE railway_system.car OWNER TO postgres;

--
-- Name: car_car_number_seq; Type: SEQUENCE; Schema: railway_system; Owner: postgres
--

CREATE SEQUENCE railway_system.car_car_number_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE railway_system.car_car_number_seq OWNER TO postgres;

--
-- Name: car_car_number_seq; Type: SEQUENCE OWNED BY; Schema: railway_system; Owner: postgres
--

ALTER SEQUENCE railway_system.car_car_number_seq OWNED BY railway_system.car.car_number;


--
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
-- Name: order; Type: TABLE; Schema: railway_system; Owner: postgres
--

CREATE TABLE railway_system."order" (
                                        order_id character varying(64) NOT NULL,
                                        train_number character varying(64) NOT NULL,
                                        car_type character varying(64) NOT NULL,
                                        price numeric NOT NULL,
                                        arrival_date timestamp without time zone NOT NULL,
                                        dispatch_date timestamp without time zone NOT NULL,
                                        user_id character varying(64) NOT NULL,
                                        order_date timestamp without time zone NOT NULL,
                                        order_status character varying(64),
                                        count_of_seats integer NOT NULL,
                                        arrival_station character varying(64) NOT NULL,
                                        dispatch_station character varying(64) NOT NULL,
                                        travel_time character varying(128) NOT NULL
);


ALTER TABLE railway_system."order" OWNER TO postgres;

--
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
-- Name: rout; Type: TABLE; Schema: railway_system; Owner: postgres
--

CREATE TABLE railway_system.rout (
                                     routs_id character varying(64) NOT NULL,
                                     train_id character varying(64) NOT NULL,
                                     rout_name character varying(128) NOT NULL,
                                     rout_number character varying(128) NOT NULL,
                                     common_free_seats_count integer,
                                     compartment_free_seats_count integer,
                                     reserved_free_seats_count integer
);


ALTER TABLE railway_system.rout OWNER TO postgres;

--
-- Name: rout_to_station_mapping; Type: TABLE; Schema: railway_system; Owner: postgres
--

CREATE TABLE railway_system.rout_to_station_mapping (
                                                        station_id character varying(64) NOT NULL,
                                                        routs_id character varying(64) NOT NULL,
                                                        station_arrival_date timestamp without time zone NOT NULL,
                                                        station_dispatch_data timestamp without time zone NOT NULL,
                                                        "order" character varying(64) NOT NULL
);


ALTER TABLE railway_system.rout_to_station_mapping OWNER TO postgres;

--
-- Name: station; Type: TABLE; Schema: railway_system; Owner: postgres
--

CREATE TABLE railway_system.station (
                                        station_id character varying(64) NOT NULL,
                                        station character varying(100) NOT NULL
);


ALTER TABLE railway_system.station OWNER TO postgres;

--
-- Name: train; Type: TABLE; Schema: railway_system; Owner: postgres
--

CREATE TABLE railway_system.train (
                                      train_id character varying(64) NOT NULL,
                                      train_number character varying(64) NOT NULL
);


ALTER TABLE railway_system.train OWNER TO postgres;

--
-- Name: user; Type: TABLE; Schema: railway_system; Owner: postgres
--

CREATE TABLE railway_system."user" (
                                       user_id character varying(64) NOT NULL,
                                       email character varying(255) NOT NULL,
                                       password character varying(255) NOT NULL,
                                       first_name character varying(25) NOT NULL,
                                       last_name character varying(25) NOT NULL,
                                       phone character varying(25) NOT NULL,
                                       birth_date date NOT NULL,
                                       role character varying(25) NOT NULL,
                                       blocked boolean NOT NULL
);


ALTER TABLE railway_system."user" OWNER TO postgres;

--
-- Data for Name: car; Type: TABLE DATA; Schema: railway_system; Owner: postgres
--

COPY railway_system.car (car_id, car_type, car_number, train_id, seats) FROM stdin;
d6374b70-4e5e-4f54-a047-015ea3778fe6	COMPARTMENT	1	f324db20-8812-4f1e-b317-faabba8fb7b4	20
e486cdb0-138b-4ed2-ab00-b9cc193a878d	COMMON	3	f324db20-8812-4f1e-b317-faabba8fb7b4	30
2fefe0d1-cc20-485c-83e4-39c9e4802ae2	COMPARTMENT	1	30336920-d115-4d60-b872-eb6e5ebfb1d5	20
bd3889a9-f185-41ec-b3ba-00c2dbbfb1c8	RESERVED_SEAT	2	f324db20-8812-4f1e-b317-faabba8fb7b4	20
2960fce8-6fd5-4c0b-b9c2-35923850971f	COMMON	3	30336920-d115-4d60-b872-eb6e5ebfb1d5	30
98fbf47e-70d5-4417-9169-b676c1b60a29	RESERVED_SEAT	2	30336920-d115-4d60-b872-eb6e5ebfb1d5	20
\.


--
-- Data for Name: order; Type: TABLE DATA; Schema: railway_system; Owner: postgres
--

COPY railway_system."order" (order_id, train_number, car_type, price, arrival_date, dispatch_date, user_id, order_date, order_status, count_of_seats, arrival_station, dispatch_station, travel_time) FROM stdin;
f5baeb10-7b69-44b8-8333-1376a53e5d7f	1	RESERVED_SEAT	400	2020-04-21 01:01:00	2020-04-21 04:44:00	4123b274-5321-40ed-9765-7a7ebcc8011e	2020-04-21 22:54:16.13	ORDER_ACCEPTED	2	Poltava	Kharkov	Days: 0 Hours: 3 Minutes: 43
082651ea-5498-4867-b434-9352229f8c5c	1	RESERVED_SEAT	400	2020-04-21 01:01:00	2020-04-21 04:44:00	e7f895e3-61cd-4a6c-ab9e-9bb4e96323ea	2020-04-22 03:53:59.385	ORDER_ACCEPTED	2	Poltava	Kharkov	Days: 0 Hours: 3 Minutes: 43
\.


--
-- Data for Name: rout; Type: TABLE DATA; Schema: railway_system; Owner: postgres
--

COPY railway_system.rout (routs_id, train_id, rout_name, rout_number, common_free_seats_count, compartment_free_seats_count, reserved_free_seats_count) FROM stdin;
42913091-c5b5-4e1e-8767-9929e681bfdc	30336920-d115-4d60-b872-eb6e5ebfb1d5	Travel Ukraine	2	30	18	20
ee103d9f-d613-4516-bc6c-e5cbabb24bc7	f324db20-8812-4f1e-b317-faabba8fb7b4	Speed Kyiv	1	26	15	16
\.


--
-- Data for Name: rout_to_station_mapping; Type: TABLE DATA; Schema: railway_system; Owner: postgres
--

COPY railway_system.rout_to_station_mapping (station_id, routs_id, station_arrival_date, station_dispatch_data, "order") FROM stdin;
5d5c1243-ba04-4921-8304-20ccb322f4a3	ee103d9f-d613-4516-bc6c-e5cbabb24bc7	2020-04-21 01:01:00	2020-04-21 01:11:00	1
4da2e815-1b7d-43c6-8b6f-825dedfb8d1a	ee103d9f-d613-4516-bc6c-e5cbabb24bc7	2020-04-21 02:02:00	2020-04-21 02:22:00	2
53eba105-0885-4c1e-9eff-e6d11aee19e6	ee103d9f-d613-4516-bc6c-e5cbabb24bc7	2020-04-21 03:03:00	2020-04-21 03:33:00	3
245ea97b-7c80-43b0-a79c-28db48e57d69	ee103d9f-d613-4516-bc6c-e5cbabb24bc7	2020-04-21 04:04:00	2020-04-21 04:44:00	4
5d5c1243-ba04-4921-8304-20ccb322f4a3	42913091-c5b5-4e1e-8767-9929e681bfdc	2020-04-21 05:04:00	2020-04-21 05:14:00	1
245ea97b-7c80-43b0-a79c-28db48e57d69	42913091-c5b5-4e1e-8767-9929e681bfdc	2020-04-21 06:01:00	2020-04-21 06:11:00	2
\.


--
-- Data for Name: station; Type: TABLE DATA; Schema: railway_system; Owner: postgres
--

COPY railway_system.station (station_id, station) FROM stdin;
5d5c1243-ba04-4921-8304-20ccb322f4a3	Kharkov
4da2e815-1b7d-43c6-8b6f-825dedfb8d1a	Odessa
245ea97b-7c80-43b0-a79c-28db48e57d69	Poltava
8a38b59a-b278-4655-8783-dd2c58b3a400	Lvov
7758b6e8-f340-4098-a128-bfeb3623afca	Symmu
53eba105-0885-4c1e-9eff-e6d11aee19e6	Krakov
\.


--
-- Data for Name: train; Type: TABLE DATA; Schema: railway_system; Owner: postgres
--

COPY railway_system.train (train_id, train_number) FROM stdin;
f324db20-8812-4f1e-b317-faabba8fb7b4	1
30336920-d115-4d60-b872-eb6e5ebfb1d5	2
3536501b-ad85-4851-94d4-ad952d129f6c	3
\.


--
-- Data for Name: user; Type: TABLE DATA; Schema: railway_system; Owner: postgres
--

COPY railway_system."user" (user_id, email, password, first_name, last_name, phone, birth_date, role, blocked) FROM stdin;
e7f895e3-61cd-4a6c-ab9e-9bb4e96323ea	admin@mail.com	admin	Admin	Admin	+380969528588	1994-02-18	ADMIN	f
9123b274-5321-40ed-3232-1e7ebcc8011e	user2@mail.com	user	User2	User2	+380969528586	1998-11-15	USER	f
4123b274-5321-40ed-9765-7a7ebcc8011e	user@mail.com	user	User	User	+380969528587	1998-11-11	USER	f
\.


--
-- Name: CarType_carTypeId_seq; Type: SEQUENCE SET; Schema: railway_system; Owner: postgres
--

SELECT pg_catalog.setval('railway_system."CarType_carTypeId_seq"', 1, false);


--
-- Name: Cars_carId_seq; Type: SEQUENCE SET; Schema: railway_system; Owner: postgres
--

SELECT pg_catalog.setval('railway_system."Cars_carId_seq"', 1, false);


--
-- Name: Order_orderId_seq; Type: SEQUENCE SET; Schema: railway_system; Owner: postgres
--

SELECT pg_catalog.setval('railway_system."Order_orderId_seq"', 1, false);


--
-- Name: Rolle_id_seq; Type: SEQUENCE SET; Schema: railway_system; Owner: postgres
--

SELECT pg_catalog.setval('railway_system."Rolle_id_seq"', 1, false);


--
-- Name: Routs_id_seq; Type: SEQUENCE SET; Schema: railway_system; Owner: postgres
--

SELECT pg_catalog.setval('railway_system."Routs_id_seq"', 1, false);


--
-- Name: StationType_id_seq; Type: SEQUENCE SET; Schema: railway_system; Owner: postgres
--

SELECT pg_catalog.setval('railway_system."StationType_id_seq"', 1, false);


--
-- Name: Stations_id_seq; Type: SEQUENCE SET; Schema: railway_system; Owner: postgres
--

SELECT pg_catalog.setval('railway_system."Stations_id_seq"', 1, false);


--
-- Name: Trains_id_seq; Type: SEQUENCE SET; Schema: railway_system; Owner: postgres
--

SELECT pg_catalog.setval('railway_system."Trains_id_seq"', 1, false);


--
-- Name: Trains_id_seq1; Type: SEQUENCE SET; Schema: railway_system; Owner: postgres
--

SELECT pg_catalog.setval('railway_system."Trains_id_seq1"', 1, false);


--
-- Name: Users_id_seq; Type: SEQUENCE SET; Schema: railway_system; Owner: postgres
--

SELECT pg_catalog.setval('railway_system."Users_id_seq"', 1, false);


--
-- Name: WayStation_id_seq; Type: SEQUENCE SET; Schema: railway_system; Owner: postgres
--

SELECT pg_catalog.setval('railway_system."WayStation_id_seq"', 1, false);


--
-- Name: car_car_number_seq; Type: SEQUENCE SET; Schema: railway_system; Owner: postgres
--

SELECT pg_catalog.setval('railway_system.car_car_number_seq', 1, false);


--
-- Name: invoices_id_seq; Type: SEQUENCE SET; Schema: railway_system; Owner: postgres
--

SELECT pg_catalog.setval('railway_system.invoices_id_seq', 1, false);


--
-- Name: prices_id_seq; Type: SEQUENCE SET; Schema: railway_system; Owner: postgres
--

SELECT pg_catalog.setval('railway_system.prices_id_seq', 1, false);


--
-- Name: car cars_pk; Type: CONSTRAINT; Schema: railway_system; Owner: postgres
--

ALTER TABLE ONLY railway_system.car
    ADD CONSTRAINT cars_pk PRIMARY KEY (car_id);


--
-- Name: order order_pk; Type: CONSTRAINT; Schema: railway_system; Owner: postgres
--

ALTER TABLE ONLY railway_system."order"
    ADD CONSTRAINT order_pk PRIMARY KEY (order_id);


--
-- Name: rout_to_station_mapping rout_to_station_mapping_pk; Type: CONSTRAINT; Schema: railway_system; Owner: postgres
--

ALTER TABLE ONLY railway_system.rout_to_station_mapping
    ADD CONSTRAINT rout_to_station_mapping_pk PRIMARY KEY (station_id, routs_id);


--
-- Name: rout routs_pkey; Type: CONSTRAINT; Schema: railway_system; Owner: postgres
--

ALTER TABLE ONLY railway_system.rout
    ADD CONSTRAINT routs_pkey PRIMARY KEY (routs_id);


--
-- Name: station stations_pkey; Type: CONSTRAINT; Schema: railway_system; Owner: postgres
--

ALTER TABLE ONLY railway_system.station
    ADD CONSTRAINT stations_pkey PRIMARY KEY (station_id);


--
-- Name: train trains_pk; Type: CONSTRAINT; Schema: railway_system; Owner: postgres
--

ALTER TABLE ONLY railway_system.train
    ADD CONSTRAINT trains_pk PRIMARY KEY (train_id);


--
-- Name: user users_pkey; Type: CONSTRAINT; Schema: railway_system; Owner: postgres
--

ALTER TABLE ONLY railway_system."user"
    ADD CONSTRAINT users_pkey PRIMARY KEY (user_id);


--
-- Name: rout_to_station_mapping routs_id; Type: FK CONSTRAINT; Schema: railway_system; Owner: postgres
--

ALTER TABLE ONLY railway_system.rout_to_station_mapping
    ADD CONSTRAINT routs_id FOREIGN KEY (routs_id) REFERENCES railway_system.rout(routs_id) MATCH FULL ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: rout_to_station_mapping station_id; Type: FK CONSTRAINT; Schema: railway_system; Owner: postgres
--

ALTER TABLE ONLY railway_system.rout_to_station_mapping
    ADD CONSTRAINT station_id FOREIGN KEY (station_id) REFERENCES railway_system.station(station_id) MATCH FULL ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: rout train_id; Type: FK CONSTRAINT; Schema: railway_system; Owner: postgres
--

ALTER TABLE ONLY railway_system.rout
    ADD CONSTRAINT train_id FOREIGN KEY (train_id) REFERENCES railway_system.train(train_id) MATCH FULL ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: car train_id; Type: FK CONSTRAINT; Schema: railway_system; Owner: postgres
--

ALTER TABLE ONLY railway_system.car
    ADD CONSTRAINT train_id FOREIGN KEY (train_id) REFERENCES railway_system.train(train_id) MATCH FULL ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: order user_id; Type: FK CONSTRAINT; Schema: railway_system; Owner: postgres
--

ALTER TABLE ONLY railway_system."order"
    ADD CONSTRAINT user_id FOREIGN KEY (user_id) REFERENCES railway_system."user"(user_id) MATCH FULL ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: station; Type: ROW SECURITY; Schema: railway_system; Owner: postgres
--

ALTER TABLE railway_system.station ENABLE ROW LEVEL SECURITY;

--
-- PostgreSQL database dump complete
--

