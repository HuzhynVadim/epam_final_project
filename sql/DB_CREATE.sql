--
-- PostgreSQL database dump
--

-- Dumped from database version 12.2
-- Dumped by pg_dump version 12.2

-- Started on 2020-04-30 05:25:00

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
-- TOC entry 4 (class 2615 OID 16565)
-- Name: railway_system; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA railway_system;


ALTER SCHEMA railway_system OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 16566)
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
-- TOC entry 204 (class 1259 OID 16568)
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
-- TOC entry 205 (class 1259 OID 16570)
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
-- TOC entry 206 (class 1259 OID 16572)
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
-- TOC entry 207 (class 1259 OID 16574)
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
-- TOC entry 208 (class 1259 OID 16576)
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
-- TOC entry 209 (class 1259 OID 16578)
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
-- TOC entry 210 (class 1259 OID 16580)
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
-- TOC entry 211 (class 1259 OID 16582)
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
-- TOC entry 212 (class 1259 OID 16584)
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
-- TOC entry 213 (class 1259 OID 16586)
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
-- TOC entry 215 (class 1259 OID 16590)
-- Name: car; Type: TABLE; Schema: railway_system; Owner: postgres
--

CREATE TABLE railway_system.car (
                                    car_id character varying(64) NOT NULL,
                                    car_type character varying(64) NOT NULL,
                                    car_number character varying(64),
                                    train_id character varying(64)
);


ALTER TABLE railway_system.car OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 16588)
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
-- TOC entry 216 (class 1259 OID 16595)
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
-- TOC entry 217 (class 1259 OID 16597)
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
                                        travel_time character varying(128) NOT NULL,
                                        routs_id character varying(64) NOT NULL,
                                        car_number character varying(64) NOT NULL
);


ALTER TABLE railway_system."order" OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 16605)
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
-- TOC entry 219 (class 1259 OID 16607)
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
-- TOC entry 220 (class 1259 OID 16612)
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
-- TOC entry 224 (class 1259 OID 16635)
-- Name: seat; Type: TABLE; Schema: railway_system; Owner: postgres
--

CREATE TABLE railway_system.seat (
                                     seat_id character varying(64) NOT NULL,
                                     car_id character varying(64),
                                     seat_number integer NOT NULL,
                                     busy boolean DEFAULT false NOT NULL
);


ALTER TABLE railway_system.seat OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 16617)
-- Name: station; Type: TABLE; Schema: railway_system; Owner: postgres
--

CREATE TABLE railway_system.station (
                                        station_id character varying(64) NOT NULL,
                                        station character varying(100) NOT NULL
);


ALTER TABLE railway_system.station OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 16622)
-- Name: train; Type: TABLE; Schema: railway_system; Owner: postgres
--

CREATE TABLE railway_system.train (
                                      train_id character varying(64) NOT NULL,
                                      train_number character varying(64) NOT NULL
);


ALTER TABLE railway_system.train OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 16627)
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
                                       blocked boolean DEFAULT false NOT NULL
);


ALTER TABLE railway_system."user" OWNER TO postgres;

--
-- TOC entry 2907 (class 0 OID 16590)
-- Dependencies: 215
-- Data for Name: car; Type: TABLE DATA; Schema: railway_system; Owner: postgres
--

COPY railway_system.car (car_id, car_type, car_number, train_id) FROM stdin;
ea5702a6-179d-4bc2-aa57-d384e19c08a9	COMPARTMENT	1	4d1399a5-1d3e-4e5a-8477-c8665dff638e
\.


--
-- TOC entry 2909 (class 0 OID 16597)
-- Dependencies: 217
-- Data for Name: order; Type: TABLE DATA; Schema: railway_system; Owner: postgres
--

COPY railway_system."order" (order_id, train_number, car_type, price, arrival_date, dispatch_date, user_id, order_date, order_status, count_of_seats, arrival_station, dispatch_station, travel_time, routs_id, car_number) FROM stdin;
\.


--
-- TOC entry 2911 (class 0 OID 16607)
-- Dependencies: 219
-- Data for Name: rout; Type: TABLE DATA; Schema: railway_system; Owner: postgres
--

COPY railway_system.rout (routs_id, train_id, rout_name, rout_number, common_free_seats_count, compartment_free_seats_count, reserved_free_seats_count) FROM stdin;
\.


--
-- TOC entry 2912 (class 0 OID 16612)
-- Dependencies: 220
-- Data for Name: rout_to_station_mapping; Type: TABLE DATA; Schema: railway_system; Owner: postgres
--

COPY railway_system.rout_to_station_mapping (station_id, routs_id, station_arrival_date, station_dispatch_data, "order") FROM stdin;
\.


--
-- TOC entry 2916 (class 0 OID 16635)
-- Dependencies: 224
-- Data for Name: seat; Type: TABLE DATA; Schema: railway_system; Owner: postgres
--

COPY railway_system.seat (seat_id, car_id, seat_number, busy) FROM stdin;
81cfa4c8-c801-442f-bcde-dcbbef2edc49	ea5702a6-179d-4bc2-aa57-d384e19c08a9	1	f
87559a93-39df-45ce-8828-98f26b769da5	ea5702a6-179d-4bc2-aa57-d384e19c08a9	2	f
e79d6ae4-df4c-44d5-a0d2-5ab76f36c83f	ea5702a6-179d-4bc2-aa57-d384e19c08a9	3	f
1dfa4d59-3c07-4cdb-8422-8b85046a5830	ea5702a6-179d-4bc2-aa57-d384e19c08a9	4	f
b3c6444f-3efd-400a-b48b-77090709360f	ea5702a6-179d-4bc2-aa57-d384e19c08a9	5	f
e80347ac-b4cd-49bc-b4b3-1691ff3f33b9	ea5702a6-179d-4bc2-aa57-d384e19c08a9	6	f
839e0d39-77a2-4657-98a0-52da183ab874	ea5702a6-179d-4bc2-aa57-d384e19c08a9	7	f
2d07de3f-3dbe-433c-a9e5-7b96141a192b	ea5702a6-179d-4bc2-aa57-d384e19c08a9	8	f
662cdc6a-843b-414e-9b49-211ca8e00829	ea5702a6-179d-4bc2-aa57-d384e19c08a9	9	f
a9a2560f-0cbf-4769-9154-36e13a35ad73	ea5702a6-179d-4bc2-aa57-d384e19c08a9	10	f
09ebb82b-d4a0-4bd5-a4d3-678b98fb88ac	ea5702a6-179d-4bc2-aa57-d384e19c08a9	11	f
65bb4948-ac95-4567-ba1e-6e61ba692054	ea5702a6-179d-4bc2-aa57-d384e19c08a9	12	f
990d71a5-35c0-44be-a0bc-c1fc2b72d488	ea5702a6-179d-4bc2-aa57-d384e19c08a9	13	f
8c837443-08e6-4293-a033-4831b5d24838	ea5702a6-179d-4bc2-aa57-d384e19c08a9	14	f
55d62005-b01c-4fce-a0f4-5c8cffa81285	ea5702a6-179d-4bc2-aa57-d384e19c08a9	15	f
af2f775e-937a-418c-ad79-13ed6c9d59c7	ea5702a6-179d-4bc2-aa57-d384e19c08a9	16	f
933fd4d3-31a2-49fa-a2f0-7de7f96fa967	ea5702a6-179d-4bc2-aa57-d384e19c08a9	17	f
a56153a4-950f-4a43-80f9-9da4c4e6b414	ea5702a6-179d-4bc2-aa57-d384e19c08a9	18	f
e2da6078-5736-4d7c-b08d-e695517cfc9e	ea5702a6-179d-4bc2-aa57-d384e19c08a9	19	f
f7359d03-cb1d-4f9d-a232-c39ec5ba3955	ea5702a6-179d-4bc2-aa57-d384e19c08a9	20	f
\.


--
-- TOC entry 2913 (class 0 OID 16617)
-- Dependencies: 221
-- Data for Name: station; Type: TABLE DATA; Schema: railway_system; Owner: postgres
--

COPY railway_system.station (station_id, station) FROM stdin;
d62981f1-f302-470b-8d6c-cc704fe82ee8	Kyiv
f28b3cd5-98ef-4eb4-bba9-7245db7c6b30	Odessa
2581d0f0-1741-47e6-8724-e72e305e05e4	Poltava
c4b774ef-41de-4ecc-a458-76dcf34c68a6	Kharkov
\.


--
-- TOC entry 2914 (class 0 OID 16622)
-- Dependencies: 222
-- Data for Name: train; Type: TABLE DATA; Schema: railway_system; Owner: postgres
--

COPY railway_system.train (train_id, train_number) FROM stdin;
4d1399a5-1d3e-4e5a-8477-c8665dff638e	1
d25160b9-d330-4d12-8da7-33e8a5255740	2
\.


--
-- TOC entry 2915 (class 0 OID 16627)
-- Dependencies: 223
-- Data for Name: user; Type: TABLE DATA; Schema: railway_system; Owner: postgres
--

COPY railway_system."user" (user_id, email, password, first_name, last_name, phone, birth_date, role, blocked) FROM stdin;
3ef46f73-e7f9-42a8-aa25-568131669766	admin@mail.com	admin	Admin	Admin	+380969528587	1992-04-30	ADMIN	f
649c24d4-6943-4116-bf27-b3af6a87a436	user@mail.com	user	User	User	+380969528589	1970-12-12	USER	f
2a9d6e12-95c4-4d49-9a0d-cdbaa9fe9ce8	user2@mail.com	user	User2	User2	+380969528586	1985-12-18	USER	f
\.


--
-- TOC entry 2922 (class 0 OID 0)
-- Dependencies: 203
-- Name: CarType_carTypeId_seq; Type: SEQUENCE SET; Schema: railway_system; Owner: postgres
--

SELECT pg_catalog.setval('railway_system."CarType_carTypeId_seq"', 1, false);


--
-- TOC entry 2923 (class 0 OID 0)
-- Dependencies: 204
-- Name: Cars_carId_seq; Type: SEQUENCE SET; Schema: railway_system; Owner: postgres
--

SELECT pg_catalog.setval('railway_system."Cars_carId_seq"', 1, false);


--
-- TOC entry 2924 (class 0 OID 0)
-- Dependencies: 205
-- Name: Order_orderId_seq; Type: SEQUENCE SET; Schema: railway_system; Owner: postgres
--

SELECT pg_catalog.setval('railway_system."Order_orderId_seq"', 1, false);


--
-- TOC entry 2925 (class 0 OID 0)
-- Dependencies: 206
-- Name: Rolle_id_seq; Type: SEQUENCE SET; Schema: railway_system; Owner: postgres
--

SELECT pg_catalog.setval('railway_system."Rolle_id_seq"', 1, false);


--
-- TOC entry 2926 (class 0 OID 0)
-- Dependencies: 207
-- Name: Routs_id_seq; Type: SEQUENCE SET; Schema: railway_system; Owner: postgres
--

SELECT pg_catalog.setval('railway_system."Routs_id_seq"', 1, false);


--
-- TOC entry 2927 (class 0 OID 0)
-- Dependencies: 208
-- Name: StationType_id_seq; Type: SEQUENCE SET; Schema: railway_system; Owner: postgres
--

SELECT pg_catalog.setval('railway_system."StationType_id_seq"', 1, false);


--
-- TOC entry 2928 (class 0 OID 0)
-- Dependencies: 209
-- Name: Stations_id_seq; Type: SEQUENCE SET; Schema: railway_system; Owner: postgres
--

SELECT pg_catalog.setval('railway_system."Stations_id_seq"', 1, false);


--
-- TOC entry 2929 (class 0 OID 0)
-- Dependencies: 210
-- Name: Trains_id_seq; Type: SEQUENCE SET; Schema: railway_system; Owner: postgres
--

SELECT pg_catalog.setval('railway_system."Trains_id_seq"', 1, false);


--
-- TOC entry 2930 (class 0 OID 0)
-- Dependencies: 211
-- Name: Trains_id_seq1; Type: SEQUENCE SET; Schema: railway_system; Owner: postgres
--

SELECT pg_catalog.setval('railway_system."Trains_id_seq1"', 1, false);


--
-- TOC entry 2931 (class 0 OID 0)
-- Dependencies: 212
-- Name: Users_id_seq; Type: SEQUENCE SET; Schema: railway_system; Owner: postgres
--

SELECT pg_catalog.setval('railway_system."Users_id_seq"', 1, false);


--
-- TOC entry 2932 (class 0 OID 0)
-- Dependencies: 213
-- Name: WayStation_id_seq; Type: SEQUENCE SET; Schema: railway_system; Owner: postgres
--

SELECT pg_catalog.setval('railway_system."WayStation_id_seq"', 1, false);


--
-- TOC entry 2933 (class 0 OID 0)
-- Dependencies: 214
-- Name: car_car_number_seq; Type: SEQUENCE SET; Schema: railway_system; Owner: postgres
--

SELECT pg_catalog.setval('railway_system.car_car_number_seq', 1, false);


--
-- TOC entry 2934 (class 0 OID 0)
-- Dependencies: 216
-- Name: invoices_id_seq; Type: SEQUENCE SET; Schema: railway_system; Owner: postgres
--

SELECT pg_catalog.setval('railway_system.invoices_id_seq', 1, false);


--
-- TOC entry 2935 (class 0 OID 0)
-- Dependencies: 218
-- Name: prices_id_seq; Type: SEQUENCE SET; Schema: railway_system; Owner: postgres
--

SELECT pg_catalog.setval('railway_system.prices_id_seq', 1, false);


--
-- TOC entry 2747 (class 2606 OID 16594)
-- Name: car cars_pk; Type: CONSTRAINT; Schema: railway_system; Owner: postgres
--

ALTER TABLE ONLY railway_system.car
    ADD CONSTRAINT cars_pk PRIMARY KEY (car_id);


--
-- TOC entry 2749 (class 2606 OID 16604)
-- Name: order order_pk; Type: CONSTRAINT; Schema: railway_system; Owner: postgres
--

ALTER TABLE ONLY railway_system."order"
    ADD CONSTRAINT order_pk PRIMARY KEY (order_id);


--
-- TOC entry 2753 (class 2606 OID 16616)
-- Name: rout_to_station_mapping rout_to_station_mapping_pk; Type: CONSTRAINT; Schema: railway_system; Owner: postgres
--

ALTER TABLE ONLY railway_system.rout_to_station_mapping
    ADD CONSTRAINT rout_to_station_mapping_pk PRIMARY KEY (station_id, routs_id);


--
-- TOC entry 2751 (class 2606 OID 16611)
-- Name: rout routs_pkey; Type: CONSTRAINT; Schema: railway_system; Owner: postgres
--

ALTER TABLE ONLY railway_system.rout
    ADD CONSTRAINT routs_pkey PRIMARY KEY (routs_id);


--
-- TOC entry 2761 (class 2606 OID 16639)
-- Name: seat seat_pk; Type: CONSTRAINT; Schema: railway_system; Owner: postgres
--

ALTER TABLE ONLY railway_system.seat
    ADD CONSTRAINT seat_pk PRIMARY KEY (seat_id);


--
-- TOC entry 2755 (class 2606 OID 16621)
-- Name: station stations_pkey; Type: CONSTRAINT; Schema: railway_system; Owner: postgres
--

ALTER TABLE ONLY railway_system.station
    ADD CONSTRAINT stations_pkey PRIMARY KEY (station_id);


--
-- TOC entry 2757 (class 2606 OID 16626)
-- Name: train trains_pk; Type: CONSTRAINT; Schema: railway_system; Owner: postgres
--

ALTER TABLE ONLY railway_system.train
    ADD CONSTRAINT trains_pk PRIMARY KEY (train_id);


--
-- TOC entry 2759 (class 2606 OID 16634)
-- Name: user users_pkey; Type: CONSTRAINT; Schema: railway_system; Owner: postgres
--

ALTER TABLE ONLY railway_system."user"
    ADD CONSTRAINT users_pkey PRIMARY KEY (user_id);


--
-- TOC entry 2767 (class 2606 OID 16665)
-- Name: seat car_id; Type: FK CONSTRAINT; Schema: railway_system; Owner: postgres
--

ALTER TABLE ONLY railway_system.seat
    ADD CONSTRAINT car_id FOREIGN KEY (car_id) REFERENCES railway_system.car(car_id) MATCH FULL ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2765 (class 2606 OID 16655)
-- Name: rout_to_station_mapping routs_id; Type: FK CONSTRAINT; Schema: railway_system; Owner: postgres
--

ALTER TABLE ONLY railway_system.rout_to_station_mapping
    ADD CONSTRAINT routs_id FOREIGN KEY (routs_id) REFERENCES railway_system.rout(routs_id) MATCH FULL ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2766 (class 2606 OID 16660)
-- Name: rout_to_station_mapping station_id; Type: FK CONSTRAINT; Schema: railway_system; Owner: postgres
--

ALTER TABLE ONLY railway_system.rout_to_station_mapping
    ADD CONSTRAINT station_id FOREIGN KEY (station_id) REFERENCES railway_system.station(station_id) MATCH FULL ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2762 (class 2606 OID 16640)
-- Name: car train_id; Type: FK CONSTRAINT; Schema: railway_system; Owner: postgres
--

ALTER TABLE ONLY railway_system.car
    ADD CONSTRAINT train_id FOREIGN KEY (train_id) REFERENCES railway_system.train(train_id) MATCH FULL ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2764 (class 2606 OID 16650)
-- Name: rout train_id; Type: FK CONSTRAINT; Schema: railway_system; Owner: postgres
--

ALTER TABLE ONLY railway_system.rout
    ADD CONSTRAINT train_id FOREIGN KEY (train_id) REFERENCES railway_system.train(train_id) MATCH FULL ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2763 (class 2606 OID 16645)
-- Name: order user_id; Type: FK CONSTRAINT; Schema: railway_system; Owner: postgres
--

ALTER TABLE ONLY railway_system."order"
    ADD CONSTRAINT user_id FOREIGN KEY (user_id) REFERENCES railway_system."user"(user_id) MATCH FULL ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2894 (class 0 OID 16617)
-- Dependencies: 221
-- Name: station; Type: ROW SECURITY; Schema: railway_system; Owner: postgres
--

ALTER TABLE railway_system.station ENABLE ROW LEVEL SECURITY;

-- Completed on 2020-04-30 05:25:00

--
-- PostgreSQL database dump complete
--

