--
-- PostgreSQL database dump
--

-- Dumped from database version 12.22 (Ubuntu 12.22-0ubuntu0.20.04.1)
-- Dumped by pg_dump version 12.22 (Ubuntu 12.22-0ubuntu0.20.04.1)

-- Started on 2024-12-19 09:30:35 -03

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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 205 (class 1259 OID 103333)
-- Name: categoria; Type: TABLE; Schema: public; Owner: smdecommerce
--

CREATE TABLE public.categoria (
    id integer NOT NULL,
    descricao character varying NOT NULL
);


ALTER TABLE public.categoria OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 103331)
-- Name: categoria_id_seq; Type: SEQUENCE; Schema: public; Owner: smdecommerce
--

CREATE SEQUENCE public.categoria_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.categoria_id_seq OWNER TO postgres;

--
-- TOC entry 2997 (class 0 OID 0)
-- Dependencies: 204
-- Name: categoria_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: smdecommerce
--

ALTER SEQUENCE public.categoria_id_seq OWNED BY public.categoria.id;


--
-- TOC entry 207 (class 1259 OID 103345)
-- Name: produto; Type: TABLE; Schema: public; Owner: smdecommerce
--

CREATE TABLE public.produto (
    id integer NOT NULL,
    descricao character varying NOT NULL,
    preco numeric NOT NULL,
    quantidade integer NOT NULL,
    foto character varying
);


ALTER TABLE public.produto OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 103343)
-- Name: produto_id_seq; Type: SEQUENCE; Schema: public; Owner: smdecommerce
--

CREATE SEQUENCE public.produto_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.produto_id_seq OWNER TO postgres;

--
-- TOC entry 2998 (class 0 OID 0)
-- Dependencies: 206
-- Name: produto_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: smdecommerce
--

ALTER SEQUENCE public.produto_id_seq OWNED BY public.produto.id;


--
-- TOC entry 203 (class 1259 OID 102642)
-- Name: usuario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuario (
    id integer NOT NULL,
    nome character varying NOT NULL,
    endereco character varying NOT NULL,
    login character varying NOT NULL,
    senha character varying NOT NULL,
    email character varying NOT NULL,
    administrador boolean NOT NULL
);


ALTER TABLE public.usuario OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 102640)
-- Name: usuario_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.usuario_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.usuario_id_seq OWNER TO postgres;

--
-- TOC entry 2999 (class 0 OID 0)
-- Dependencies: 202
-- Name: usuario_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.usuario_id_seq OWNED BY public.usuario.id;


--
-- TOC entry 2850 (class 2604 OID 103336)
-- Name: categoria id; Type: DEFAULT; Schema: public; Owner: smdecommerce
--

ALTER TABLE ONLY public.categoria ALTER COLUMN id SET DEFAULT nextval('public.categoria_id_seq'::regclass);


--
-- TOC entry 2851 (class 2604 OID 103348)
-- Name: produto id; Type: DEFAULT; Schema: public; Owner: smdecommerce
--

ALTER TABLE ONLY public.produto ALTER COLUMN id SET DEFAULT nextval('public.produto_id_seq'::regclass);


--
-- TOC entry 2849 (class 2604 OID 102645)
-- Name: usuario id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario ALTER COLUMN id SET DEFAULT nextval('public.usuario_id_seq'::regclass);


--
-- TOC entry 2989 (class 0 OID 103333)
-- Dependencies: 205
-- Data for Name: categoria; Type: TABLE DATA; Schema: public; Owner: smdecommerce
--



--
-- TOC entry 2991 (class 0 OID 103345)
-- Dependencies: 207
-- Data for Name: produto; Type: TABLE DATA; Schema: public; Owner: smdecommerce
--

INSERT INTO public.produto VALUES (2, 'Playstation 3 SLIM', 990.9, 15, '');
INSERT INTO public.produto VALUES (1, 'XBOX One', 1599.95, 3, '');
INSERT INTO public.produto VALUES (8, 'Atari', 555, 60, '');
INSERT INTO public.produto VALUES (4, 'Playstation 5', 3599.9, 1, '');


--
-- TOC entry 2987 (class 0 OID 102642)
-- Dependencies: 203
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.usuario VALUES (1, 'LEONARDO OLIVEIRA MOREIRA', 'RUA X', 'leoomoreira', 'ufc123', 'leoomoreira@ufc.br', true);
INSERT INTO public.usuario VALUES (3, '', '', '', '', '', false);


--
-- TOC entry 3000 (class 0 OID 0)
-- Dependencies: 204
-- Name: categoria_id_seq; Type: SEQUENCE SET; Schema: public; Owner: smdecommerce
--

SELECT pg_catalog.setval('public.categoria_id_seq', 1, false);


--
-- TOC entry 3001 (class 0 OID 0)
-- Dependencies: 206
-- Name: produto_id_seq; Type: SEQUENCE SET; Schema: public; Owner: smdecommerce
--

SELECT pg_catalog.setval('public.produto_id_seq', 8, true);


--
-- TOC entry 3002 (class 0 OID 0)
-- Dependencies: 202
-- Name: usuario_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.usuario_id_seq', 2, false);


--
-- TOC entry 2857 (class 2606 OID 103341)
-- Name: categoria categoria_pkey; Type: CONSTRAINT; Schema: public; Owner: smdecommerce
--

ALTER TABLE ONLY public.categoria
    ADD CONSTRAINT categoria_pkey PRIMARY KEY (id);


--
-- TOC entry 2859 (class 2606 OID 103353)
-- Name: produto produto_pkey; Type: CONSTRAINT; Schema: public; Owner: smdecommerce
--

ALTER TABLE ONLY public.produto
    ADD CONSTRAINT produto_pkey PRIMARY KEY (id);


--
-- TOC entry 2853 (class 2606 OID 102652)
-- Name: usuario usuario_login_ukey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_login_ukey UNIQUE (login);


--
-- TOC entry 2855 (class 2606 OID 102650)
-- Name: usuario usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id);


-- Completed on 2024-12-19 09:30:35 -03

--
-- PostgreSQL database dump complete
--

