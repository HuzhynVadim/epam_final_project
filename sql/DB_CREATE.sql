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
-- Name: final_project; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE final_project WITH ENCODING = 'UTF8' LC_COLLATE = 'Russian_Ukraine.1251' LC_CTYPE = 'Russian_Ukraine.1251';


ALTER DATABASE final_project OWNER TO postgres;

\connect final_project

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
                                    train_id character varying(64)
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
                                        travel_time character varying(128) NOT NULL,
                                        routs_id character varying(64) NOT NULL,
                                        car_number character varying(64) NOT NULL,
                                        seat_number character varying(64) NOT NULL,
                                        seats_id character varying(8000) NOT NULL
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
                                     rout_number character varying(128) NOT NULL
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
                                       blocked boolean DEFAULT false NOT NULL
);


ALTER TABLE railway_system."user" OWNER TO postgres;

--
-- Data for Name: car; Type: TABLE DATA; Schema: railway_system; Owner: postgres
--

INSERT INTO railway_system.car (car_id, car_type, car_number, train_id) VALUES ('a9449e26-0162-49a7-9c55-cf42b416e3f1', 'COMPARTMENT', '2', '49c1d109-8331-48e8-b31c-cc623037e1a7');
INSERT INTO railway_system.car (car_id, car_type, car_number, train_id) VALUES ('84b99a5e-ecda-4412-a268-545f24874c2f', 'COMMON', '3', '49c1d109-8331-48e8-b31c-cc623037e1a7');
INSERT INTO railway_system.car (car_id, car_type, car_number, train_id) VALUES ('a450ae6d-8eb4-4d3a-8101-31036aa72322', 'COMMON', '3', '0c58aef6-d1a6-42f4-99f5-a877c032ea94');
INSERT INTO railway_system.car (car_id, car_type, car_number, train_id) VALUES ('96107c6d-7334-4d99-adda-ffc74638aec5', 'COMPARTMENT', '1', '49c1d109-8331-48e8-b31c-cc623037e1a7');
INSERT INTO railway_system.car (car_id, car_type, car_number, train_id) VALUES ('25d6d80a-8e62-456a-8f66-3a0353480639', 'RESERVED_SEAT', '1', '0c58aef6-d1a6-42f4-99f5-a877c032ea94');
INSERT INTO railway_system.car (car_id, car_type, car_number, train_id) VALUES ('0e3d424a-b216-418b-93fb-1d3340f802b8', 'RESERVED_SEAT', '2', '0c58aef6-d1a6-42f4-99f5-a877c032ea94');


--
-- Data for Name: order; Type: TABLE DATA; Schema: railway_system; Owner: postgres
--

INSERT INTO railway_system."order" (order_id, train_number, car_type, price, arrival_date, dispatch_date, user_id, order_date, order_status, count_of_seats, arrival_station, dispatch_station, travel_time, routs_id, car_number, seat_number, seats_id) VALUES ('33543fdd-a477-4ea8-b805-9213c34c2377', '1', 'COMPARTMENT', 600, '2020-05-06 12:22:00', '2020-05-06 18:18:00', '57e8f74f-14f5-4e5b-a5c4-242548108e9b', '2020-05-05 00:19:59.313', 'ORDER_ACCEPTED', 2, 'Odessa', 'Kharkov', 'Дней: 0 Часов: 5 Минут: 56', 'e4b0e61f-e5b1-4b0a-a8f3-6dc67fb7ce5d', '2', '1 2 ', '2d4ce3d3-b5e3-47d7-9a6f-be029199e19a 0956391c-0ad7-46bb-a470-7bd58fc2b6ad ');
INSERT INTO railway_system."order" (order_id, train_number, car_type, price, arrival_date, dispatch_date, user_id, order_date, order_status, count_of_seats, arrival_station, dispatch_station, travel_time, routs_id, car_number, seat_number, seats_id) VALUES ('24c7635e-3149-4a54-84a6-8f4b9d0743eb', '1', 'COMMON', 400, '2020-05-06 12:22:00', '2020-05-06 18:18:00', '0a56842d-d949-42e3-99d1-2da0f7bfc9d1', '2020-05-05 00:23:06.416', 'ORDER_CANCELED', 4, 'Odessa', 'Kharkov', 'Дней: 0 Часов: 5 Минут: 56', 'e4b0e61f-e5b1-4b0a-a8f3-6dc67fb7ce5d', '3', '5 2 1 4 ', '10446bee-0489-4b1e-a394-262508757245 cb42ca39-42a1-430f-a4b5-0ab54b4b6f80 375677b0-fcd4-4050-8d92-8e5390cfb599 b152a15c-6dd9-4a96-8ab8-47c5a6ba208d ');
INSERT INTO railway_system."order" (order_id, train_number, car_type, price, arrival_date, dispatch_date, user_id, order_date, order_status, count_of_seats, arrival_station, dispatch_station, travel_time, routs_id, car_number, seat_number, seats_id) VALUES ('0999eda6-024c-4b74-86b1-c66a2ac76188', '1', 'COMMON', 600, '2020-05-06 12:22:00', '2020-05-06 18:18:00', '57e8f74f-14f5-4e5b-a5c4-242548108e9b', '2020-05-05 00:41:51.966', 'ORDER_DECLINED', 6, 'Odessa', 'Kharkov', 'Дней: 0 Часов: 5 Минут: 56', 'e4b0e61f-e5b1-4b0a-a8f3-6dc67fb7ce5d', '3', '3 6 5 2 1 4 ', '2f540a6c-3724-44e7-b4ee-c3e7dc85891f 1821d72b-b127-4276-92ac-69e9f9ed518c 10446bee-0489-4b1e-a394-262508757245 cb42ca39-42a1-430f-a4b5-0ab54b4b6f80 375677b0-fcd4-4050-8d92-8e5390cfb599 b152a15c-6dd9-4a96-8ab8-47c5a6ba208d ');


--
-- Data for Name: rout; Type: TABLE DATA; Schema: railway_system; Owner: postgres
--

INSERT INTO railway_system.rout (routs_id, train_id, rout_name, rout_number) VALUES ('e4b0e61f-e5b1-4b0a-a8f3-6dc67fb7ce5d', '49c1d109-8331-48e8-b31c-cc623037e1a7', 'Speed Kyiv', '1');
INSERT INTO railway_system.rout (routs_id, train_id, rout_name, rout_number) VALUES ('58713365-0e81-464b-ae99-98f63f02789d', '0c58aef6-d1a6-42f4-99f5-a877c032ea94', 'Travel Ukraine', '2');


--
-- Data for Name: rout_to_station_mapping; Type: TABLE DATA; Schema: railway_system; Owner: postgres
--

INSERT INTO railway_system.rout_to_station_mapping (station_id, routs_id, station_arrival_date, station_dispatch_data, "order") VALUES ('d5eada62-cd50-4d72-a2f5-64ed7869e10f', 'e4b0e61f-e5b1-4b0a-a8f3-6dc67fb7ce5d', '2020-05-06 15:16:00', '2020-05-06 16:16:00', '3');
INSERT INTO railway_system.rout_to_station_mapping (station_id, routs_id, station_arrival_date, station_dispatch_data, "order") VALUES ('e7e71469-a996-4580-b3f9-d09aea62ba53', 'e4b0e61f-e5b1-4b0a-a8f3-6dc67fb7ce5d', '2020-05-06 18:18:00', '2020-05-06 18:55:00', '4');
INSERT INTO railway_system.rout_to_station_mapping (station_id, routs_id, station_arrival_date, station_dispatch_data, "order") VALUES ('cc3347cc-b731-4736-8803-8ea0e8dc1ce4', '58713365-0e81-464b-ae99-98f63f02789d', '2020-05-06 16:16:00', '2020-05-06 16:55:00', '1');
INSERT INTO railway_system.rout_to_station_mapping (station_id, routs_id, station_arrival_date, station_dispatch_data, "order") VALUES ('e7e71469-a996-4580-b3f9-d09aea62ba53', '58713365-0e81-464b-ae99-98f63f02789d', '2020-05-06 22:22:00', '2020-05-06 22:55:00', '2');
INSERT INTO railway_system.rout_to_station_mapping (station_id, routs_id, station_arrival_date, station_dispatch_data, "order") VALUES ('2953e5e0-7682-4482-b48b-2dc1aa6d4f82', 'e4b0e61f-e5b1-4b0a-a8f3-6dc67fb7ce5d', '2020-05-06 12:00:00', '2020-05-06 12:22:00', '1');
INSERT INTO railway_system.rout_to_station_mapping (station_id, routs_id, station_arrival_date, station_dispatch_data, "order") VALUES ('cc3347cc-b731-4736-8803-8ea0e8dc1ce4', 'e4b0e61f-e5b1-4b0a-a8f3-6dc67fb7ce5d', '2020-05-06 13:02:00', '2020-05-06 13:17:00', '2');


--
-- Data for Name: seat; Type: TABLE DATA; Schema: railway_system; Owner: postgres
--

INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('284c7d36-86ac-43b5-a628-2e9b8ef63ded', '96107c6d-7334-4d99-adda-ffc74638aec5', 2, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('8c01b476-e920-4ca2-98b3-c078ea06f8b4', '96107c6d-7334-4d99-adda-ffc74638aec5', 1, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('9001994d-a05b-469e-b4b8-2f914765a86d', '96107c6d-7334-4d99-adda-ffc74638aec5', 4, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('6c2108e0-8b09-4bb1-b107-6dba55cf0340', '96107c6d-7334-4d99-adda-ffc74638aec5', 3, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('375677b0-fcd4-4050-8d92-8e5390cfb599', '84b99a5e-ecda-4412-a268-545f24874c2f', 1, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('b152a15c-6dd9-4a96-8ab8-47c5a6ba208d', '84b99a5e-ecda-4412-a268-545f24874c2f', 4, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('c4354afd-11c0-445b-9308-e1824b8825a0', '84b99a5e-ecda-4412-a268-545f24874c2f', 7, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('9d6e2217-2543-462a-99e2-2c6b4f18aaba', '84b99a5e-ecda-4412-a268-545f24874c2f', 8, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('b85cbcaf-fab6-42f1-925b-5b1bc21eca80', '84b99a5e-ecda-4412-a268-545f24874c2f', 9, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('c0527386-1947-46bc-be3a-cde3468590d3', '84b99a5e-ecda-4412-a268-545f24874c2f', 10, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('79df6db0-e316-4607-989a-30bde26fc8b9', '84b99a5e-ecda-4412-a268-545f24874c2f', 11, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('c0acfd7c-4466-47f7-9876-9d5db4c44821', '84b99a5e-ecda-4412-a268-545f24874c2f', 12, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('1ff00e53-f4bb-4821-9461-47cd7cc6c772', '84b99a5e-ecda-4412-a268-545f24874c2f', 13, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('adf26702-c283-4683-a475-7763755683e9', '84b99a5e-ecda-4412-a268-545f24874c2f', 14, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('3794d311-f60d-4d36-93b3-9f476091c34c', '84b99a5e-ecda-4412-a268-545f24874c2f', 15, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('e8940952-94d0-40f1-ab6f-cd23960655e0', '84b99a5e-ecda-4412-a268-545f24874c2f', 16, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('0051423f-a659-41ff-807b-7ab545f5a25f', '84b99a5e-ecda-4412-a268-545f24874c2f', 17, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('f940986a-e655-4c7a-9cc3-44179eaffd30', '84b99a5e-ecda-4412-a268-545f24874c2f', 18, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('5295f9b4-95bf-439a-8a6d-9ab50e0f356b', '84b99a5e-ecda-4412-a268-545f24874c2f', 19, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('9286a17f-9b46-4c69-990c-ba844d58d749', '84b99a5e-ecda-4412-a268-545f24874c2f', 20, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('afe8fad1-7091-4ead-a9bb-44da4698f4c3', '84b99a5e-ecda-4412-a268-545f24874c2f', 21, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('6b3575c5-826f-4750-8a38-eba53033a096', '84b99a5e-ecda-4412-a268-545f24874c2f', 22, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('b2713456-39ef-41f9-9145-0df790bbb688', '84b99a5e-ecda-4412-a268-545f24874c2f', 23, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('0069ee69-4a8c-4104-8dad-d71fd1769ef8', '84b99a5e-ecda-4412-a268-545f24874c2f', 24, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('b901a531-f989-4867-86e7-ea1c68249f4f', '84b99a5e-ecda-4412-a268-545f24874c2f', 25, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('3710a8a6-0060-4a69-afae-4d703f7017b8', '84b99a5e-ecda-4412-a268-545f24874c2f', 26, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('f2a113aa-b594-44d1-b482-a05eda848eb4', '84b99a5e-ecda-4412-a268-545f24874c2f', 27, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('b0c2f0b9-9244-4082-ab43-3a9e0c1ce9e2', '84b99a5e-ecda-4412-a268-545f24874c2f', 28, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('a675bdc8-14e9-47ab-af93-3c5d6e40b407', '84b99a5e-ecda-4412-a268-545f24874c2f', 29, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('d9888761-f06f-4db6-b5c2-1c93905158a3', '84b99a5e-ecda-4412-a268-545f24874c2f', 30, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('a410604e-abfa-4339-8f51-ec3b81dda12a', '96107c6d-7334-4d99-adda-ffc74638aec5', 5, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('45785d29-ff9c-46be-94f0-348bf1b92325', '96107c6d-7334-4d99-adda-ffc74638aec5', 6, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('19d2f55a-b2e9-499a-9e1f-3107020a19aa', '96107c6d-7334-4d99-adda-ffc74638aec5', 7, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('578aad4e-2a6b-43af-b8ba-68fecd986fff', '96107c6d-7334-4d99-adda-ffc74638aec5', 8, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('f6940cb7-d80f-4a18-95ef-c9e065f64740', '96107c6d-7334-4d99-adda-ffc74638aec5', 9, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('30799fcc-1a9c-4e17-99ef-806b9afe83ee', '96107c6d-7334-4d99-adda-ffc74638aec5', 10, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('2f540a6c-3724-44e7-b4ee-c3e7dc85891f', '84b99a5e-ecda-4412-a268-545f24874c2f', 3, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('1821d72b-b127-4276-92ac-69e9f9ed518c', '84b99a5e-ecda-4412-a268-545f24874c2f', 6, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('10446bee-0489-4b1e-a394-262508757245', '84b99a5e-ecda-4412-a268-545f24874c2f', 5, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('cb42ca39-42a1-430f-a4b5-0ab54b4b6f80', '84b99a5e-ecda-4412-a268-545f24874c2f', 2, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('884e8492-038d-46e7-849e-5d8b4435bf78', '96107c6d-7334-4d99-adda-ffc74638aec5', 11, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('eee8cb02-3e19-4966-8b30-b42a3600b1c6', '96107c6d-7334-4d99-adda-ffc74638aec5', 12, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('cf9fd5f1-ef46-4f31-8136-f5f64aaecbee', '96107c6d-7334-4d99-adda-ffc74638aec5', 13, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('0eb0b186-5079-4169-8e7d-40d8d8cd29c7', '96107c6d-7334-4d99-adda-ffc74638aec5', 14, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('3c918bf8-75a6-4486-8d0d-fb1211468132', '96107c6d-7334-4d99-adda-ffc74638aec5', 15, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('902366e4-b41e-4067-ba73-e2bf0c7ebd1d', '96107c6d-7334-4d99-adda-ffc74638aec5', 16, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('feed3a9a-b2ef-416a-9b80-a889ca00000e', '96107c6d-7334-4d99-adda-ffc74638aec5', 17, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('760603d5-042b-4183-86da-891272f8cde7', '96107c6d-7334-4d99-adda-ffc74638aec5', 18, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('d33d115b-6848-42c1-833e-615e60131675', '96107c6d-7334-4d99-adda-ffc74638aec5', 19, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('705e304d-4638-48ff-a2d0-78e4f3499581', '96107c6d-7334-4d99-adda-ffc74638aec5', 20, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('50e5d12c-daf6-44c5-892f-6d7f16f54d32', '0e3d424a-b216-418b-93fb-1d3340f802b8', 4, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('17b789f1-2b51-4b80-a6b6-664880df5843', '0e3d424a-b216-418b-93fb-1d3340f802b8', 5, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('acb55cdf-5716-4c0b-8250-a27aa6e793d0', '0e3d424a-b216-418b-93fb-1d3340f802b8', 6, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('0e679052-a580-45cc-9dc9-da3e2bf40bb9', '0e3d424a-b216-418b-93fb-1d3340f802b8', 7, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('74382271-29b4-4700-92a0-be202e2cdeee', '0e3d424a-b216-418b-93fb-1d3340f802b8', 8, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('39c36d7d-247b-4e5f-9c99-efb1c13b3d49', '0e3d424a-b216-418b-93fb-1d3340f802b8', 9, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('12877fe4-bc34-4bc9-a283-5b1827a2d892', '0e3d424a-b216-418b-93fb-1d3340f802b8', 10, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('379de932-cc0f-470c-b882-b6a279aab992', '0e3d424a-b216-418b-93fb-1d3340f802b8', 11, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('b9908b19-c312-4343-8c8a-e840d427eb5b', '0e3d424a-b216-418b-93fb-1d3340f802b8', 12, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('46157d00-d4d8-4444-908a-bd25e2f0cb02', '0e3d424a-b216-418b-93fb-1d3340f802b8', 13, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('64cbbdf5-d203-4efd-9174-bfbb423019dc', '0e3d424a-b216-418b-93fb-1d3340f802b8', 14, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('dc2465fc-7c42-40de-ae42-224b87f5fa14', '0e3d424a-b216-418b-93fb-1d3340f802b8', 15, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('59d2d6bf-8ef5-4b77-bc6f-60b6a71d7cb1', '0e3d424a-b216-418b-93fb-1d3340f802b8', 16, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('48d2b990-d8e7-43b8-a4e6-165cf0fb9f28', '0e3d424a-b216-418b-93fb-1d3340f802b8', 17, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('50cb98bf-88b0-463a-b926-e66e3b57f962', '0e3d424a-b216-418b-93fb-1d3340f802b8', 18, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('f261c667-04f0-4c5e-821b-c522c0b3ce6a', '0e3d424a-b216-418b-93fb-1d3340f802b8', 19, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('c643a320-452e-4c6e-9308-9f1099bf066c', '0e3d424a-b216-418b-93fb-1d3340f802b8', 20, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('0e1b4c52-d19f-42e7-83ea-c362de3b8358', '0e3d424a-b216-418b-93fb-1d3340f802b8', 21, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('3b9daf61-1e70-4c29-a5b0-ae1300e39952', '0e3d424a-b216-418b-93fb-1d3340f802b8', 22, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('5da12020-e281-4589-8430-070990243282', '0e3d424a-b216-418b-93fb-1d3340f802b8', 23, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('5433aa68-c818-4e95-ae7f-32c40cddddf2', '0e3d424a-b216-418b-93fb-1d3340f802b8', 24, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('752b86cc-fe0e-40c5-86db-c71488385722', '0e3d424a-b216-418b-93fb-1d3340f802b8', 25, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('0910d8f4-cce9-4add-a940-0db76d56cd3e', '0e3d424a-b216-418b-93fb-1d3340f802b8', 26, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('b4105064-7e9c-462a-adbf-95de4c6371e6', '0e3d424a-b216-418b-93fb-1d3340f802b8', 27, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('45247c5a-e11d-43e3-8021-e78b7492367a', '0e3d424a-b216-418b-93fb-1d3340f802b8', 28, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('d8660db6-a716-42f3-81d8-c0b16f416f53', '0e3d424a-b216-418b-93fb-1d3340f802b8', 29, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('8b894b61-9863-41b4-95b7-6a80bf40d773', '0e3d424a-b216-418b-93fb-1d3340f802b8', 30, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('1c7c5e98-a0f9-4737-af89-326429211177', '0e3d424a-b216-418b-93fb-1d3340f802b8', 3, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('92c16d22-4665-44ca-b99b-9147dcd5ac9d', '0e3d424a-b216-418b-93fb-1d3340f802b8', 2, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('aaf1b355-ebdd-429d-af4c-2562051ddbab', '25d6d80a-8e62-456a-8f66-3a0353480639', 5, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('7bfaef4a-366b-4f34-ae44-352d97675b2f', '25d6d80a-8e62-456a-8f66-3a0353480639', 6, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('8ed86b11-02f3-4f23-b5ad-751666eff592', '25d6d80a-8e62-456a-8f66-3a0353480639', 7, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('bca2b270-8cc2-4bd7-802e-a2655d06f36c', '25d6d80a-8e62-456a-8f66-3a0353480639', 8, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('da43c86f-59f2-4850-aa26-d0121462b701', '25d6d80a-8e62-456a-8f66-3a0353480639', 9, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('d49a681f-9e08-4829-a270-5b550ed31fc2', '25d6d80a-8e62-456a-8f66-3a0353480639', 10, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('1f28f8bd-1b37-425d-aa87-0415eecdb30b', '25d6d80a-8e62-456a-8f66-3a0353480639', 11, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('c7f1136c-5454-42b3-a19a-794344c856ab', '25d6d80a-8e62-456a-8f66-3a0353480639', 12, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('1371a3f8-1537-4817-baac-9b27c023395a', '25d6d80a-8e62-456a-8f66-3a0353480639', 13, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('481df305-1ac0-437a-8e3b-1708bce97372', '25d6d80a-8e62-456a-8f66-3a0353480639', 14, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('b8433178-ab2f-4f8e-9876-1dc8a7cc3997', '25d6d80a-8e62-456a-8f66-3a0353480639', 15, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('ce2388f2-1547-488b-ad6d-7f01adb43fa7', '25d6d80a-8e62-456a-8f66-3a0353480639', 16, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('7c302c9a-affd-4149-a795-21168cc3b9c0', '25d6d80a-8e62-456a-8f66-3a0353480639', 17, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('7b924122-6b1c-4b8b-8291-02bafd22b1c3', '25d6d80a-8e62-456a-8f66-3a0353480639', 18, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('77056b73-c97c-4fd7-9916-8531d57b30c2', '25d6d80a-8e62-456a-8f66-3a0353480639', 19, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('720d286a-5e98-4d39-8d06-4189b76df741', '25d6d80a-8e62-456a-8f66-3a0353480639', 20, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('f315b538-24ad-4a39-886b-3bd91bc05a5e', '25d6d80a-8e62-456a-8f66-3a0353480639', 21, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('bfbbe845-fe56-44d5-b012-4e020df86424', '25d6d80a-8e62-456a-8f66-3a0353480639', 22, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('611c4d0e-6b53-4873-ab48-a6564634aa87', '25d6d80a-8e62-456a-8f66-3a0353480639', 23, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('115125e3-92da-4eea-8606-ff7841325baf', '25d6d80a-8e62-456a-8f66-3a0353480639', 24, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('2f3e92ae-ee1c-444b-ad64-1572bab24992', '25d6d80a-8e62-456a-8f66-3a0353480639', 25, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('d8af6200-db1c-4168-8e13-172ad0c4884f', '25d6d80a-8e62-456a-8f66-3a0353480639', 26, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('8c58ff34-d595-43ae-b3c3-64a43fb28d9d', '25d6d80a-8e62-456a-8f66-3a0353480639', 27, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('aca0be21-f695-426f-b42a-3f730abc9d48', '25d6d80a-8e62-456a-8f66-3a0353480639', 28, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('4800bc8b-e8fb-4e27-81e7-67444a788ea8', '25d6d80a-8e62-456a-8f66-3a0353480639', 29, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('df45bb3f-5b78-4324-af54-2d4f7227feb8', '25d6d80a-8e62-456a-8f66-3a0353480639', 30, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('718f66b9-8dec-4436-a0ca-6a9537ccac34', '84b99a5e-ecda-4412-a268-545f24874c2f', 31, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('54b49fc1-7ec3-43da-a24e-6c98806b7220', '84b99a5e-ecda-4412-a268-545f24874c2f', 32, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('73635028-8e8c-4740-932e-2e42539e4e1a', '84b99a5e-ecda-4412-a268-545f24874c2f', 33, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('df0f9e78-0863-49b5-bb43-90bf58b3f1d9', '84b99a5e-ecda-4412-a268-545f24874c2f', 34, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('af398973-e031-4a1d-b19f-781886351927', '84b99a5e-ecda-4412-a268-545f24874c2f', 35, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('78304c63-7a12-4cab-ba5b-96d067ad3ddb', '84b99a5e-ecda-4412-a268-545f24874c2f', 36, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('55db13e1-96d1-4848-8d51-aa1e760d2ff4', '84b99a5e-ecda-4412-a268-545f24874c2f', 37, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('09dbbb87-559e-4fec-8569-50a0198ec212', '84b99a5e-ecda-4412-a268-545f24874c2f', 38, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('88bb8c88-87d0-43e9-a1d2-79c1af7c254c', '84b99a5e-ecda-4412-a268-545f24874c2f', 39, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('8f845aec-16d9-475e-8a5a-415d98c15c54', '84b99a5e-ecda-4412-a268-545f24874c2f', 40, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('ef264416-91d6-4864-9eeb-5129128cc282', '25d6d80a-8e62-456a-8f66-3a0353480639', 1, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('9cd142cc-b310-496d-8cf8-0055a4b58825', '25d6d80a-8e62-456a-8f66-3a0353480639', 2, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('5de88ac0-ff74-4e10-8b82-679732f77e0c', '25d6d80a-8e62-456a-8f66-3a0353480639', 3, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('54bd4c4d-9ae2-4642-9ec7-70090e62ad37', '25d6d80a-8e62-456a-8f66-3a0353480639', 4, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('a19aa90a-085c-46e5-936e-80f26bdd27e6', '0e3d424a-b216-418b-93fb-1d3340f802b8', 1, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('fa94c9d0-4a97-4ec3-bb4f-bfa75b57b0df', 'a450ae6d-8eb4-4d3a-8101-31036aa72322', 1, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('40f2d0ad-968b-4d86-a699-31d9cce80527', 'a450ae6d-8eb4-4d3a-8101-31036aa72322', 2, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('031dba36-e9a1-4530-9139-cbc4b04b09bb', 'a450ae6d-8eb4-4d3a-8101-31036aa72322', 3, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('414432d4-782c-4018-807a-a98383e8138e', 'a450ae6d-8eb4-4d3a-8101-31036aa72322', 4, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('b3e63456-6591-4a89-b894-2cdb1a08a210', 'a450ae6d-8eb4-4d3a-8101-31036aa72322', 5, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('3bf4d375-9a1e-410b-9679-e48a4909fbed', 'a450ae6d-8eb4-4d3a-8101-31036aa72322', 6, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('a44cb937-cdb0-432e-8e72-d704f3cedc4a', 'a450ae6d-8eb4-4d3a-8101-31036aa72322', 7, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('6616807a-8446-4b29-aaa6-d2284592bad3', 'a450ae6d-8eb4-4d3a-8101-31036aa72322', 8, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('a2a8cd1e-535a-4601-9186-1d19d0f3b4f1', 'a450ae6d-8eb4-4d3a-8101-31036aa72322', 9, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('6a1ae638-669d-446e-b4c8-354b1d45b0ae', 'a450ae6d-8eb4-4d3a-8101-31036aa72322', 10, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('eb3dbb8c-063e-48db-aa9e-ac1de8661985', 'a450ae6d-8eb4-4d3a-8101-31036aa72322', 11, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('c30332af-e185-45be-afb3-52754f82d775', 'a450ae6d-8eb4-4d3a-8101-31036aa72322', 12, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('f84ef787-c050-4fcc-98eb-5b6e9b6bd217', 'a450ae6d-8eb4-4d3a-8101-31036aa72322', 13, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('92cb533a-0656-4c17-b726-c2b6235883a5', 'a450ae6d-8eb4-4d3a-8101-31036aa72322', 14, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('eeaa8278-9d31-4eb1-8375-adc43e20d573', 'a450ae6d-8eb4-4d3a-8101-31036aa72322', 15, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('ee7d4330-d97c-4b81-ae7b-d975c0327666', 'a450ae6d-8eb4-4d3a-8101-31036aa72322', 16, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('ca71191d-0140-4321-9b1d-c759a16fdf5d', 'a450ae6d-8eb4-4d3a-8101-31036aa72322', 17, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('0064d5b7-915d-490a-ae17-3cf0ac6e8b91', 'a450ae6d-8eb4-4d3a-8101-31036aa72322', 18, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('bc91408e-7c01-40fa-82e2-336de916c9ed', 'a450ae6d-8eb4-4d3a-8101-31036aa72322', 19, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('8763d406-27d3-40fa-bcc3-d0d1d2de86b6', 'a450ae6d-8eb4-4d3a-8101-31036aa72322', 20, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('5b83bd6d-4a65-4ed5-87c0-6bd6fd324330', 'a450ae6d-8eb4-4d3a-8101-31036aa72322', 21, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('ecf555cc-f56b-4fca-80df-2a3b3252b53d', 'a450ae6d-8eb4-4d3a-8101-31036aa72322', 22, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('6608ebe8-f89f-464a-a723-519437495178', 'a450ae6d-8eb4-4d3a-8101-31036aa72322', 23, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('628da4f3-b091-4277-aa49-d334c1154dfc', 'a450ae6d-8eb4-4d3a-8101-31036aa72322', 24, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('35c4270b-a989-4179-bac9-09f052f0f8da', 'a450ae6d-8eb4-4d3a-8101-31036aa72322', 25, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('b4183815-7e4a-428a-b409-dffdcea87d6b', 'a450ae6d-8eb4-4d3a-8101-31036aa72322', 26, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('fe3b05a6-d479-4dd9-bf87-8dc3dd4410cb', 'a450ae6d-8eb4-4d3a-8101-31036aa72322', 27, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('6efb83d7-7349-4d0c-b67b-11615b8c3058', 'a450ae6d-8eb4-4d3a-8101-31036aa72322', 28, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('b439ce82-c2bb-42ea-a31a-1e34e95d0448', 'a450ae6d-8eb4-4d3a-8101-31036aa72322', 29, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('8e4ecb84-78d7-4cf5-b2f8-65668bcf3554', 'a450ae6d-8eb4-4d3a-8101-31036aa72322', 30, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('3336579d-1c0d-4423-a97b-9b1bd94b4db1', 'a450ae6d-8eb4-4d3a-8101-31036aa72322', 31, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('c641591e-78a8-46bf-90a8-4cad5e700d6d', 'a450ae6d-8eb4-4d3a-8101-31036aa72322', 32, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('f2fa62d2-02ce-4149-bc98-f3c890bcca8d', 'a450ae6d-8eb4-4d3a-8101-31036aa72322', 33, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('ade16928-bb3a-42ab-9254-c6011c08a0fc', 'a450ae6d-8eb4-4d3a-8101-31036aa72322', 34, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('ee5bc00e-1170-4011-9bd3-7fb8b304c0b1', 'a450ae6d-8eb4-4d3a-8101-31036aa72322', 35, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('4dbe30ca-cc72-43ce-95a7-19c9617f5484', 'a450ae6d-8eb4-4d3a-8101-31036aa72322', 36, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('75fede96-8e01-44a0-bd1f-00429631a181', 'a450ae6d-8eb4-4d3a-8101-31036aa72322', 37, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('e640885a-1f6d-4011-9f85-df4a29f1bfb1', 'a450ae6d-8eb4-4d3a-8101-31036aa72322', 38, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('4b161edb-544f-4dfd-94a0-ee47060938a8', 'a450ae6d-8eb4-4d3a-8101-31036aa72322', 39, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('7e4ea885-905c-419d-91c3-ec5ada377fa6', 'a450ae6d-8eb4-4d3a-8101-31036aa72322', 40, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('2d4ce3d3-b5e3-47d7-9a6f-be029199e19a', 'a9449e26-0162-49a7-9c55-cf42b416e3f1', 1, true);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('0956391c-0ad7-46bb-a470-7bd58fc2b6ad', 'a9449e26-0162-49a7-9c55-cf42b416e3f1', 2, true);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('28ff033d-e6ef-420b-9c0d-6974e3bb76db', 'a9449e26-0162-49a7-9c55-cf42b416e3f1', 3, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('83417d32-21be-4ce9-aa01-19da86154c7a', 'a9449e26-0162-49a7-9c55-cf42b416e3f1', 4, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('4adce9af-61d4-4c92-b218-41aa05848cd5', 'a9449e26-0162-49a7-9c55-cf42b416e3f1', 5, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('1fda46c0-814e-4127-977c-4d3e8ca6e859', 'a9449e26-0162-49a7-9c55-cf42b416e3f1', 6, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('8ddf413f-fe56-4f6c-8cd1-cc449891e23f', 'a9449e26-0162-49a7-9c55-cf42b416e3f1', 7, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('a727a0c3-a8d2-498c-9798-08aa32099d32', 'a9449e26-0162-49a7-9c55-cf42b416e3f1', 8, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('b7ee457e-f934-44da-8265-775d0d4fb2c0', 'a9449e26-0162-49a7-9c55-cf42b416e3f1', 9, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('0c96585a-33cf-4dea-9dae-ac416b16da16', 'a9449e26-0162-49a7-9c55-cf42b416e3f1', 10, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('ce4f9e62-c17a-460e-a9bb-789ed98d5713', 'a9449e26-0162-49a7-9c55-cf42b416e3f1', 11, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('a35cb905-4b6c-4b33-aaf4-b545e1a06809', 'a9449e26-0162-49a7-9c55-cf42b416e3f1', 12, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('7c415d95-789c-40fd-a95e-74d899b6fb32', 'a9449e26-0162-49a7-9c55-cf42b416e3f1', 13, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('00c3efda-dcc8-4a61-8358-ab18c9764b71', 'a9449e26-0162-49a7-9c55-cf42b416e3f1', 14, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('cb7414c4-536d-433d-9937-48035ef8175b', 'a9449e26-0162-49a7-9c55-cf42b416e3f1', 15, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('ef508153-3f6c-4fea-b3ec-94337836da41', 'a9449e26-0162-49a7-9c55-cf42b416e3f1', 16, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('1d0ce853-99ff-420e-8118-606c738e55b4', 'a9449e26-0162-49a7-9c55-cf42b416e3f1', 17, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('c802a5aa-6a2a-419c-9ff6-8ea5c23b614f', 'a9449e26-0162-49a7-9c55-cf42b416e3f1', 18, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('a8f5779f-565b-4a78-84c0-26f3e148ae45', 'a9449e26-0162-49a7-9c55-cf42b416e3f1', 19, false);
INSERT INTO railway_system.seat (seat_id, car_id, seat_number, busy) VALUES ('2a9d731e-7a64-4f0b-bb18-ee98c79d1d77', 'a9449e26-0162-49a7-9c55-cf42b416e3f1', 20, false);


--
-- Data for Name: station; Type: TABLE DATA; Schema: railway_system; Owner: postgres
--

INSERT INTO railway_system.station (station_id, station) VALUES ('2953e5e0-7682-4482-b48b-2dc1aa6d4f82', 'Kharkov');
INSERT INTO railway_system.station (station_id, station) VALUES ('cc3347cc-b731-4736-8803-8ea0e8dc1ce4', 'Kyiv');
INSERT INTO railway_system.station (station_id, station) VALUES ('d5eada62-cd50-4d72-a2f5-64ed7869e10f', 'Poltava');
INSERT INTO railway_system.station (station_id, station) VALUES ('6b97e96b-2257-4eb0-977b-bc0f596b7a3b', 'Lvov');
INSERT INTO railway_system.station (station_id, station) VALUES ('e7e71469-a996-4580-b3f9-d09aea62ba53', 'Odessa');


--
-- Data for Name: train; Type: TABLE DATA; Schema: railway_system; Owner: postgres
--

INSERT INTO railway_system.train (train_id, train_number) VALUES ('49c1d109-8331-48e8-b31c-cc623037e1a7', '1');
INSERT INTO railway_system.train (train_id, train_number) VALUES ('0c58aef6-d1a6-42f4-99f5-a877c032ea94', '2');


--
-- Data for Name: user; Type: TABLE DATA; Schema: railway_system; Owner: postgres
--

INSERT INTO railway_system."user" (user_id, email, password, first_name, last_name, phone, birth_date, role, blocked) VALUES ('f4722cb5-131b-4a92-aae4-bfa9b2fd7b9a', 'user2@mail.com', 'user', 'Userios', 'Userios', '+380965467832', '1966-11-13', 'USER', false);
INSERT INTO railway_system."user" (user_id, email, password, first_name, last_name, phone, birth_date, role, blocked) VALUES ('0a56842d-d949-42e3-99d1-2da0f7bfc9d1', 'user@mail.com', 'user', 'User', 'User', '+380969528581', '1970-12-12', 'USER', false);
INSERT INTO railway_system."user" (user_id, email, password, first_name, last_name, phone, birth_date, role, blocked) VALUES ('57e8f74f-14f5-4e5b-a5c4-242548108e9b', 'admin@mail.com', 'admin', 'Admin', 'Admin', '+380969528583', '1988-12-12', 'ADMIN', false);


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
-- Name: seat seat_pk; Type: CONSTRAINT; Schema: railway_system; Owner: postgres
--

ALTER TABLE ONLY railway_system.seat
    ADD CONSTRAINT seat_pk PRIMARY KEY (seat_id);


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
-- Name: seat car_id; Type: FK CONSTRAINT; Schema: railway_system; Owner: postgres
--

ALTER TABLE ONLY railway_system.seat
    ADD CONSTRAINT car_id FOREIGN KEY (car_id) REFERENCES railway_system.car(car_id) MATCH FULL ON UPDATE RESTRICT ON DELETE RESTRICT;


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
-- Name: car train_id; Type: FK CONSTRAINT; Schema: railway_system; Owner: postgres
--

ALTER TABLE ONLY railway_system.car
    ADD CONSTRAINT train_id FOREIGN KEY (train_id) REFERENCES railway_system.train(train_id) MATCH FULL ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: rout train_id; Type: FK CONSTRAINT; Schema: railway_system; Owner: postgres
--

ALTER TABLE ONLY railway_system.rout
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

