--
-- PostgreSQL database dump
--

-- Started on 2011-01-21 20:15:44 CET

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = public, pg_catalog;

--
-- TOC entry 302 (class 1247 OID 16482)
-- Dependencies: 3
-- Name: order_state; Type: TYPE; Schema: public; Owner: h3m3r
--

CREATE TYPE order_state AS ENUM (
    'processing',
    'shipped',
    'delivered',
    'cancelled'
);


ALTER TYPE public.order_state OWNER TO h3m3r;

--
-- TOC entry 304 (class 1247 OID 16488)
-- Dependencies: 3
-- Name: product_state; Type: TYPE; Schema: public; Owner: h3m3r
--

CREATE TYPE product_state AS ENUM (
    'in stock',
    'out of stock'
);


ALTER TYPE public.product_state OWNER TO h3m3r;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 1537 (class 1259 OID 21002)
-- Dependencies: 3
-- Name: addresses; Type: TABLE; Schema: public; Owner: h3m3r; Tablespace: 
--

CREATE TABLE addresses (
    id bigint NOT NULL,
    street character varying,
    house_number character varying(50) NOT NULL,
    city character varying(50) NOT NULL,
    post_code character varying(10) NOT NULL,
    country character varying(100) NOT NULL
);


ALTER TABLE public.addresses OWNER TO h3m3r;

--
-- TOC entry 1538 (class 1259 OID 21008)
-- Dependencies: 1537 3
-- Name: addresses_id_seq; Type: SEQUENCE; Schema: public; Owner: h3m3r
--

CREATE SEQUENCE addresses_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.addresses_id_seq OWNER TO h3m3r;

--
-- TOC entry 1924 (class 0 OID 0)
-- Dependencies: 1538
-- Name: addresses_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: h3m3r
--

ALTER SEQUENCE addresses_id_seq OWNED BY addresses.id;


--
-- TOC entry 1925 (class 0 OID 0)
-- Dependencies: 1538
-- Name: addresses_id_seq; Type: SEQUENCE SET; Schema: public; Owner: h3m3r
--

SELECT pg_catalog.setval('addresses_id_seq', 1, false);


--
-- TOC entry 1539 (class 1259 OID 21010)
-- Dependencies: 3
-- Name: administrator_role; Type: TABLE; Schema: public; Owner: h3m3r; Tablespace: 
--

CREATE TABLE administrator_role (
    administrator bigint NOT NULL,
    role integer NOT NULL
);


ALTER TABLE public.administrator_role OWNER TO h3m3r;

--
-- TOC entry 1540 (class 1259 OID 21013)
-- Dependencies: 1839 3
-- Name: administrators; Type: TABLE; Schema: public; Owner: h3m3r; Tablespace: 
--

CREATE TABLE administrators (
    id bigint NOT NULL,
    archived boolean DEFAULT false NOT NULL
);


ALTER TABLE public.administrators OWNER TO h3m3r;

--
-- TOC entry 1560 (class 1259 OID 21206)
-- Dependencies: 3
-- Name: categories; Type: TABLE; Schema: public; Owner: h3m3r; Tablespace: 
--

CREATE TABLE categories (
    id bigint NOT NULL,
    name character varying(30) NOT NULL
);


ALTER TABLE public.categories OWNER TO h3m3r;

--
-- TOC entry 1559 (class 1259 OID 21204)
-- Dependencies: 1560 3
-- Name: categories_id_seq; Type: SEQUENCE; Schema: public; Owner: h3m3r
--

CREATE SEQUENCE categories_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.categories_id_seq OWNER TO h3m3r;

--
-- TOC entry 1926 (class 0 OID 0)
-- Dependencies: 1559
-- Name: categories_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: h3m3r
--

ALTER SEQUENCE categories_id_seq OWNED BY categories.id;


--
-- TOC entry 1927 (class 0 OID 0)
-- Dependencies: 1559
-- Name: categories_id_seq; Type: SEQUENCE SET; Schema: public; Owner: h3m3r
--

SELECT pg_catalog.setval('categories_id_seq', 1, true);


--
-- TOC entry 1541 (class 1259 OID 21017)
-- Dependencies: 3
-- Name: delivery_types; Type: TABLE; Schema: public; Owner: h3m3r; Tablespace: 
--

CREATE TABLE delivery_types (
    id integer NOT NULL,
    name character varying(100) NOT NULL,
    description text,
    price numeric(19,2)
);


ALTER TABLE public.delivery_types OWNER TO h3m3r;

--
-- TOC entry 1542 (class 1259 OID 21023)
-- Dependencies: 3 1541
-- Name: delivery_types_id_seq; Type: SEQUENCE; Schema: public; Owner: h3m3r
--

CREATE SEQUENCE delivery_types_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.delivery_types_id_seq OWNER TO h3m3r;

--
-- TOC entry 1928 (class 0 OID 0)
-- Dependencies: 1542
-- Name: delivery_types_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: h3m3r
--

ALTER SEQUENCE delivery_types_id_seq OWNED BY delivery_types.id;


--
-- TOC entry 1929 (class 0 OID 0)
-- Dependencies: 1542
-- Name: delivery_types_id_seq; Type: SEQUENCE SET; Schema: public; Owner: h3m3r
--

SELECT pg_catalog.setval('delivery_types_id_seq', 1, true);


--
-- TOC entry 1545 (class 1259 OID 21034)
-- Dependencies: 3
-- Name: order_line_items; Type: TABLE; Schema: public; Owner: h3m3r; Tablespace: 
--

CREATE TABLE order_line_items (
    id bigint NOT NULL,
    count integer NOT NULL,
    purchase_order bigint NOT NULL,
    product bigint NOT NULL
);


ALTER TABLE public.order_line_items OWNER TO h3m3r;

--
-- TOC entry 1546 (class 1259 OID 21037)
-- Dependencies: 3 1545
-- Name: order_line_items_id_seq; Type: SEQUENCE; Schema: public; Owner: h3m3r
--

CREATE SEQUENCE order_line_items_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.order_line_items_id_seq OWNER TO h3m3r;

--
-- TOC entry 1930 (class 0 OID 0)
-- Dependencies: 1546
-- Name: order_line_items_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: h3m3r
--

ALTER SEQUENCE order_line_items_id_seq OWNED BY order_line_items.id;


--
-- TOC entry 1931 (class 0 OID 0)
-- Dependencies: 1546
-- Name: order_line_items_id_seq; Type: SEQUENCE SET; Schema: public; Owner: h3m3r
--

SELECT pg_catalog.setval('order_line_items_id_seq', 1, false);


--
-- TOC entry 1543 (class 1259 OID 21025)
-- Dependencies: 1841 3
-- Name: orders; Type: TABLE; Schema: public; Owner: h3m3r; Tablespace: 
--

CREATE TABLE orders (
    id bigint NOT NULL,
    created timestamp without time zone DEFAULT ('now'::text)::timestamp without time zone NOT NULL,
    description text,
    delivery_type integer NOT NULL,
    payment_type integer NOT NULL,
    state character varying(30) NOT NULL,
    created_by bigint NOT NULL
);


ALTER TABLE public.orders OWNER TO h3m3r;

--
-- TOC entry 1544 (class 1259 OID 21032)
-- Dependencies: 3 1543
-- Name: orders_id_seq; Type: SEQUENCE; Schema: public; Owner: h3m3r
--

CREATE SEQUENCE orders_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.orders_id_seq OWNER TO h3m3r;

--
-- TOC entry 1932 (class 0 OID 0)
-- Dependencies: 1544
-- Name: orders_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: h3m3r
--

ALTER SEQUENCE orders_id_seq OWNED BY orders.id;


--
-- TOC entry 1933 (class 0 OID 0)
-- Dependencies: 1544
-- Name: orders_id_seq; Type: SEQUENCE SET; Schema: public; Owner: h3m3r
--

SELECT pg_catalog.setval('orders_id_seq', 1, false);


--
-- TOC entry 1547 (class 1259 OID 21039)
-- Dependencies: 3
-- Name: payment_types; Type: TABLE; Schema: public; Owner: h3m3r; Tablespace: 
--

CREATE TABLE payment_types (
    id integer NOT NULL,
    name character varying(100) NOT NULL,
    description text
);


ALTER TABLE public.payment_types OWNER TO h3m3r;

--
-- TOC entry 1548 (class 1259 OID 21045)
-- Dependencies: 1547 3
-- Name: payment_types_id_seq; Type: SEQUENCE; Schema: public; Owner: h3m3r
--

CREATE SEQUENCE payment_types_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.payment_types_id_seq OWNER TO h3m3r;

--
-- TOC entry 1934 (class 0 OID 0)
-- Dependencies: 1548
-- Name: payment_types_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: h3m3r
--

ALTER SEQUENCE payment_types_id_seq OWNED BY payment_types.id;


--
-- TOC entry 1935 (class 0 OID 0)
-- Dependencies: 1548
-- Name: payment_types_id_seq; Type: SEQUENCE SET; Schema: public; Owner: h3m3r
--

SELECT pg_catalog.setval('payment_types_id_seq', 1, true);


--
-- TOC entry 1549 (class 1259 OID 21047)
-- Dependencies: 1845 1846 3
-- Name: photos; Type: TABLE; Schema: public; Owner: h3m3r; Tablespace: 
--

CREATE TABLE photos (
    id bigint NOT NULL,
    name character varying(100) NOT NULL,
    ph_order smallint DEFAULT 0 NOT NULL,
    created timestamp without time zone DEFAULT ('now'::text)::timestamp without time zone NOT NULL,
    product bigint NOT NULL,
    created_by bigint NOT NULL,
    title character varying(100)
);


ALTER TABLE public.photos OWNER TO h3m3r;

--
-- TOC entry 1550 (class 1259 OID 21052)
-- Dependencies: 1549 3
-- Name: photos_id_seq; Type: SEQUENCE; Schema: public; Owner: h3m3r
--

CREATE SEQUENCE photos_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.photos_id_seq OWNER TO h3m3r;

--
-- TOC entry 1936 (class 0 OID 0)
-- Dependencies: 1550
-- Name: photos_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: h3m3r
--

ALTER SEQUENCE photos_id_seq OWNED BY photos.id;


--
-- TOC entry 1937 (class 0 OID 0)
-- Dependencies: 1550
-- Name: photos_id_seq; Type: SEQUENCE SET; Schema: public; Owner: h3m3r
--

SELECT pg_catalog.setval('photos_id_seq', 14, true);


--
-- TOC entry 1551 (class 1259 OID 21054)
-- Dependencies: 1848 1849 3
-- Name: products; Type: TABLE; Schema: public; Owner: h3m3r; Tablespace: 
--

CREATE TABLE products (
    id bigint NOT NULL,
    name character varying NOT NULL,
    description text NOT NULL,
    in_action boolean DEFAULT false NOT NULL,
    state character varying(30) NOT NULL,
    created timestamp without time zone DEFAULT ('now'::text)::timestamp without time zone NOT NULL,
    created_by bigint NOT NULL,
    category bigint NOT NULL,
    price numeric(19,2) NOT NULL,
    action_price numeric(19,2)
);


ALTER TABLE public.products OWNER TO h3m3r;

--
-- TOC entry 1552 (class 1259 OID 21062)
-- Dependencies: 1551 3
-- Name: products_id_seq; Type: SEQUENCE; Schema: public; Owner: h3m3r
--

CREATE SEQUENCE products_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.products_id_seq OWNER TO h3m3r;

--
-- TOC entry 1938 (class 0 OID 0)
-- Dependencies: 1552
-- Name: products_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: h3m3r
--

ALTER SEQUENCE products_id_seq OWNED BY products.id;


--
-- TOC entry 1939 (class 0 OID 0)
-- Dependencies: 1552
-- Name: products_id_seq; Type: SEQUENCE SET; Schema: public; Owner: h3m3r
--

SELECT pg_catalog.setval('products_id_seq', 2, true);


--
-- TOC entry 1554 (class 1259 OID 21067)
-- Dependencies: 3
-- Name: registered_user_product; Type: TABLE; Schema: public; Owner: h3m3r; Tablespace: 
--

CREATE TABLE registered_user_product (
    registered_user bigint NOT NULL,
    product bigint NOT NULL
);


ALTER TABLE public.registered_user_product OWNER TO h3m3r;

--
-- TOC entry 1553 (class 1259 OID 21064)
-- Dependencies: 3
-- Name: registered_users; Type: TABLE; Schema: public; Owner: h3m3r; Tablespace: 
--

CREATE TABLE registered_users (
    id bigint NOT NULL,
    delivery_address bigint NOT NULL,
    billing_address bigint
);


ALTER TABLE public.registered_users OWNER TO h3m3r;

--
-- TOC entry 1555 (class 1259 OID 21070)
-- Dependencies: 3
-- Name: roles; Type: TABLE; Schema: public; Owner: h3m3r; Tablespace: 
--

CREATE TABLE roles (
    id integer NOT NULL,
    rolename character varying(50) NOT NULL
);


ALTER TABLE public.roles OWNER TO h3m3r;

--
-- TOC entry 1556 (class 1259 OID 21073)
-- Dependencies: 1555 3
-- Name: roles_id_seq; Type: SEQUENCE; Schema: public; Owner: h3m3r
--

CREATE SEQUENCE roles_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.roles_id_seq OWNER TO h3m3r;

--
-- TOC entry 1940 (class 0 OID 0)
-- Dependencies: 1556
-- Name: roles_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: h3m3r
--

ALTER SEQUENCE roles_id_seq OWNED BY roles.id;


--
-- TOC entry 1941 (class 0 OID 0)
-- Dependencies: 1556
-- Name: roles_id_seq; Type: SEQUENCE SET; Schema: public; Owner: h3m3r
--

SELECT pg_catalog.setval('roles_id_seq', 2, true);


--
-- TOC entry 1557 (class 1259 OID 21075)
-- Dependencies: 1852 3
-- Name: users; Type: TABLE; Schema: public; Owner: h3m3r; Tablespace: 
--

CREATE TABLE users (
    id bigint NOT NULL,
    username character varying(20) NOT NULL,
    password character varying NOT NULL,
    firstname character varying(50) NOT NULL,
    surname character varying(50) NOT NULL,
    email character varying NOT NULL,
    inserted timestamp without time zone DEFAULT ('now'::text)::timestamp without time zone NOT NULL,
    last_logged timestamp without time zone
);


ALTER TABLE public.users OWNER TO h3m3r;

--
-- TOC entry 1558 (class 1259 OID 21082)
-- Dependencies: 3 1557
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: h3m3r
--

CREATE SEQUENCE users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.users_id_seq OWNER TO h3m3r;

--
-- TOC entry 1942 (class 0 OID 0)
-- Dependencies: 1558
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: h3m3r
--

ALTER SEQUENCE users_id_seq OWNED BY users.id;


--
-- TOC entry 1943 (class 0 OID 0)
-- Dependencies: 1558
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: h3m3r
--

SELECT pg_catalog.setval('users_id_seq', 4, true);


--
-- TOC entry 1838 (class 2604 OID 21084)
-- Dependencies: 1538 1537
-- Name: id; Type: DEFAULT; Schema: public; Owner: h3m3r
--

ALTER TABLE addresses ALTER COLUMN id SET DEFAULT nextval('addresses_id_seq'::regclass);


--
-- TOC entry 1854 (class 2604 OID 21209)
-- Dependencies: 1560 1559 1560
-- Name: id; Type: DEFAULT; Schema: public; Owner: h3m3r
--

ALTER TABLE categories ALTER COLUMN id SET DEFAULT nextval('categories_id_seq'::regclass);


--
-- TOC entry 1840 (class 2604 OID 21085)
-- Dependencies: 1542 1541
-- Name: id; Type: DEFAULT; Schema: public; Owner: h3m3r
--

ALTER TABLE delivery_types ALTER COLUMN id SET DEFAULT nextval('delivery_types_id_seq'::regclass);


--
-- TOC entry 1843 (class 2604 OID 21086)
-- Dependencies: 1546 1545
-- Name: id; Type: DEFAULT; Schema: public; Owner: h3m3r
--

ALTER TABLE order_line_items ALTER COLUMN id SET DEFAULT nextval('order_line_items_id_seq'::regclass);


--
-- TOC entry 1842 (class 2604 OID 21087)
-- Dependencies: 1544 1543
-- Name: id; Type: DEFAULT; Schema: public; Owner: h3m3r
--

ALTER TABLE orders ALTER COLUMN id SET DEFAULT nextval('orders_id_seq'::regclass);


--
-- TOC entry 1844 (class 2604 OID 21088)
-- Dependencies: 1548 1547
-- Name: id; Type: DEFAULT; Schema: public; Owner: h3m3r
--

ALTER TABLE payment_types ALTER COLUMN id SET DEFAULT nextval('payment_types_id_seq'::regclass);


--
-- TOC entry 1847 (class 2604 OID 21089)
-- Dependencies: 1550 1549
-- Name: id; Type: DEFAULT; Schema: public; Owner: h3m3r
--

ALTER TABLE photos ALTER COLUMN id SET DEFAULT nextval('photos_id_seq'::regclass);


--
-- TOC entry 1850 (class 2604 OID 21090)
-- Dependencies: 1552 1551
-- Name: id; Type: DEFAULT; Schema: public; Owner: h3m3r
--

ALTER TABLE products ALTER COLUMN id SET DEFAULT nextval('products_id_seq'::regclass);


--
-- TOC entry 1851 (class 2604 OID 21091)
-- Dependencies: 1556 1555
-- Name: id; Type: DEFAULT; Schema: public; Owner: h3m3r
--

ALTER TABLE roles ALTER COLUMN id SET DEFAULT nextval('roles_id_seq'::regclass);


--
-- TOC entry 1853 (class 2604 OID 21092)
-- Dependencies: 1558 1557
-- Name: id; Type: DEFAULT; Schema: public; Owner: h3m3r
--

ALTER TABLE users ALTER COLUMN id SET DEFAULT nextval('users_id_seq'::regclass);


--
-- TOC entry 1906 (class 0 OID 21002)
-- Dependencies: 1537
-- Data for Name: addresses; Type: TABLE DATA; Schema: public; Owner: h3m3r
--

COPY addresses (id, street, house_number, city, post_code, country) FROM stdin;
\.


--
-- TOC entry 1907 (class 0 OID 21010)
-- Dependencies: 1539
-- Data for Name: administrator_role; Type: TABLE DATA; Schema: public; Owner: h3m3r
--

COPY administrator_role (administrator, role) FROM stdin;
1	1
4	1
\.


--
-- TOC entry 1908 (class 0 OID 21013)
-- Dependencies: 1540
-- Data for Name: administrators; Type: TABLE DATA; Schema: public; Owner: h3m3r
--

COPY administrators (id, archived) FROM stdin;
1	f
4	f
\.


--
-- TOC entry 1919 (class 0 OID 21206)
-- Dependencies: 1560
-- Data for Name: categories; Type: TABLE DATA; Schema: public; Owner: h3m3r
--

COPY categories (id, name) FROM stdin;
0	pocitace
1	cxbdg555
\.


--
-- TOC entry 1909 (class 0 OID 21017)
-- Dependencies: 1541
-- Data for Name: delivery_types; Type: TABLE DATA; Schema: public; Owner: h3m3r
--

COPY delivery_types (id, name, description, price) FROM stdin;
1	pošššta	vbfghfgh	19999999999999999.99
\.


--
-- TOC entry 1911 (class 0 OID 21034)
-- Dependencies: 1545
-- Data for Name: order_line_items; Type: TABLE DATA; Schema: public; Owner: h3m3r
--

COPY order_line_items (id, count, purchase_order, product) FROM stdin;
\.


--
-- TOC entry 1910 (class 0 OID 21025)
-- Dependencies: 1543
-- Data for Name: orders; Type: TABLE DATA; Schema: public; Owner: h3m3r
--

COPY orders (id, created, description, delivery_type, payment_type, state, created_by) FROM stdin;
\.


--
-- TOC entry 1912 (class 0 OID 21039)
-- Dependencies: 1547
-- Data for Name: payment_types; Type: TABLE DATA; Schema: public; Owner: h3m3r
--

COPY payment_types (id, name, description) FROM stdin;
\.


--
-- TOC entry 1913 (class 0 OID 21047)
-- Dependencies: 1549
-- Data for Name: photos; Type: TABLE DATA; Schema: public; Owner: h3m3r
--

COPY photos (id, name, ph_order, created, product, created_by, title) FROM stdin;
6	.jpg	1	2011-01-21 11:41:04.366473	1	1	
7	.jpg	2	2011-01-21 11:57:03.152115	1	1	
14	.jpg	3	2011-01-21 20:07:16.843661	1	4	gfjhgfh
\.


--
-- TOC entry 1914 (class 0 OID 21054)
-- Dependencies: 1551
-- Data for Name: products; Type: TABLE DATA; Schema: public; Owner: h3m3r
--

COPY products (id, name, description, in_action, state, created, created_by, category, price, action_price) FROM stdin;
1	dsfgfgdsfg	dfghgdfgdfsg	f	OUT_OF_STOCK	2011-01-21 09:49:36.066027	1	1	99.90	\N
\.


--
-- TOC entry 1916 (class 0 OID 21067)
-- Dependencies: 1554
-- Data for Name: registered_user_product; Type: TABLE DATA; Schema: public; Owner: h3m3r
--

COPY registered_user_product (registered_user, product) FROM stdin;
\.


--
-- TOC entry 1915 (class 0 OID 21064)
-- Dependencies: 1553
-- Data for Name: registered_users; Type: TABLE DATA; Schema: public; Owner: h3m3r
--

COPY registered_users (id, delivery_address, billing_address) FROM stdin;
\.


--
-- TOC entry 1917 (class 0 OID 21070)
-- Dependencies: 1555
-- Data for Name: roles; Type: TABLE DATA; Schema: public; Owner: h3m3r
--

COPY roles (id, rolename) FROM stdin;
1	admin
2	salesman
\.


--
-- TOC entry 1918 (class 0 OID 21075)
-- Dependencies: 1557
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: h3m3r
--

COPY users (id, username, password, firstname, surname, email, inserted, last_logged) FROM stdin;
1	hemo	cdZ7pH7rP9JNUMc+Kn7UFl1WfJHgWr+2kCyED7kK0d4=	Václav	Chalupa565	vac.chalupa@gmail.com	2011-01-20 08:47:21.830924	\N
4	kolo	q4MxrclVDNBRCRsdv38G5Um6dMTjydmb53NT5KyGOJY=	jhgkj	sfsd	hop.jip@jup.com	2011-01-21 20:01:34.015849	\N
\.


--
-- TOC entry 1856 (class 2606 OID 21094)
-- Dependencies: 1537 1537
-- Name: pk_address; Type: CONSTRAINT; Schema: public; Owner: h3m3r; Tablespace: 
--

ALTER TABLE ONLY addresses
    ADD CONSTRAINT pk_address PRIMARY KEY (id);


--
-- TOC entry 1858 (class 2606 OID 21096)
-- Dependencies: 1540 1540
-- Name: pk_administrator; Type: CONSTRAINT; Schema: public; Owner: h3m3r; Tablespace: 
--

ALTER TABLE ONLY administrators
    ADD CONSTRAINT pk_administrator PRIMARY KEY (id);


--
-- TOC entry 1886 (class 2606 OID 21211)
-- Dependencies: 1560 1560
-- Name: pk_category; Type: CONSTRAINT; Schema: public; Owner: h3m3r; Tablespace: 
--

ALTER TABLE ONLY categories
    ADD CONSTRAINT pk_category PRIMARY KEY (id);


--
-- TOC entry 1860 (class 2606 OID 21098)
-- Dependencies: 1541 1541
-- Name: pk_delivery_type; Type: CONSTRAINT; Schema: public; Owner: h3m3r; Tablespace: 
--

ALTER TABLE ONLY delivery_types
    ADD CONSTRAINT pk_delivery_type PRIMARY KEY (id);


--
-- TOC entry 1864 (class 2606 OID 21100)
-- Dependencies: 1543 1543
-- Name: pk_order; Type: CONSTRAINT; Schema: public; Owner: h3m3r; Tablespace: 
--

ALTER TABLE ONLY orders
    ADD CONSTRAINT pk_order PRIMARY KEY (id);


--
-- TOC entry 1866 (class 2606 OID 21102)
-- Dependencies: 1545 1545
-- Name: pk_order_line_item; Type: CONSTRAINT; Schema: public; Owner: h3m3r; Tablespace: 
--

ALTER TABLE ONLY order_line_items
    ADD CONSTRAINT pk_order_line_item PRIMARY KEY (id);


--
-- TOC entry 1868 (class 2606 OID 21104)
-- Dependencies: 1547 1547
-- Name: pk_payment_type; Type: CONSTRAINT; Schema: public; Owner: h3m3r; Tablespace: 
--

ALTER TABLE ONLY payment_types
    ADD CONSTRAINT pk_payment_type PRIMARY KEY (id);


--
-- TOC entry 1872 (class 2606 OID 21106)
-- Dependencies: 1549 1549
-- Name: pk_photo; Type: CONSTRAINT; Schema: public; Owner: h3m3r; Tablespace: 
--

ALTER TABLE ONLY photos
    ADD CONSTRAINT pk_photo PRIMARY KEY (id);


--
-- TOC entry 1874 (class 2606 OID 21108)
-- Dependencies: 1551 1551
-- Name: pk_product; Type: CONSTRAINT; Schema: public; Owner: h3m3r; Tablespace: 
--

ALTER TABLE ONLY products
    ADD CONSTRAINT pk_product PRIMARY KEY (id);


--
-- TOC entry 1876 (class 2606 OID 21110)
-- Dependencies: 1553 1553
-- Name: pk_registered_user; Type: CONSTRAINT; Schema: public; Owner: h3m3r; Tablespace: 
--

ALTER TABLE ONLY registered_users
    ADD CONSTRAINT pk_registered_user PRIMARY KEY (id);


--
-- TOC entry 1878 (class 2606 OID 21112)
-- Dependencies: 1555 1555
-- Name: pk_role; Type: CONSTRAINT; Schema: public; Owner: h3m3r; Tablespace: 
--

ALTER TABLE ONLY roles
    ADD CONSTRAINT pk_role PRIMARY KEY (id);


--
-- TOC entry 1882 (class 2606 OID 21114)
-- Dependencies: 1557 1557
-- Name: pk_user; Type: CONSTRAINT; Schema: public; Owner: h3m3r; Tablespace: 
--

ALTER TABLE ONLY users
    ADD CONSTRAINT pk_user PRIMARY KEY (id);


--
-- TOC entry 1888 (class 2606 OID 21213)
-- Dependencies: 1560 1560
-- Name: uq_category_name; Type: CONSTRAINT; Schema: public; Owner: h3m3r; Tablespace: 
--

ALTER TABLE ONLY categories
    ADD CONSTRAINT uq_category_name UNIQUE (name);


--
-- TOC entry 1862 (class 2606 OID 21116)
-- Dependencies: 1541 1541
-- Name: uq_name; Type: CONSTRAINT; Schema: public; Owner: h3m3r; Tablespace: 
--

ALTER TABLE ONLY delivery_types
    ADD CONSTRAINT uq_name UNIQUE (name);


--
-- TOC entry 1870 (class 2606 OID 21118)
-- Dependencies: 1547 1547
-- Name: uq_pt_name; Type: CONSTRAINT; Schema: public; Owner: h3m3r; Tablespace: 
--

ALTER TABLE ONLY payment_types
    ADD CONSTRAINT uq_pt_name UNIQUE (name);


--
-- TOC entry 1880 (class 2606 OID 21120)
-- Dependencies: 1555 1555
-- Name: uq_rolename; Type: CONSTRAINT; Schema: public; Owner: h3m3r; Tablespace: 
--

ALTER TABLE ONLY roles
    ADD CONSTRAINT uq_rolename UNIQUE (rolename);


--
-- TOC entry 1884 (class 2606 OID 21122)
-- Dependencies: 1557 1557
-- Name: uq_username; Type: CONSTRAINT; Schema: public; Owner: h3m3r; Tablespace: 
--

ALTER TABLE ONLY users
    ADD CONSTRAINT uq_username UNIQUE (username);


--
-- TOC entry 1891 (class 2606 OID 21123)
-- Dependencies: 1540 1557 1881
-- Name: fk_ad_user; Type: FK CONSTRAINT; Schema: public; Owner: h3m3r
--

ALTER TABLE ONLY administrators
    ADD CONSTRAINT fk_ad_user FOREIGN KEY (id) REFERENCES users(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1889 (class 2606 OID 21128)
-- Dependencies: 1540 1857 1539
-- Name: fk_ar_administrator; Type: FK CONSTRAINT; Schema: public; Owner: h3m3r
--

ALTER TABLE ONLY administrator_role
    ADD CONSTRAINT fk_ar_administrator FOREIGN KEY (administrator) REFERENCES administrators(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1890 (class 2606 OID 21133)
-- Dependencies: 1555 1539 1877
-- Name: fk_ar_role; Type: FK CONSTRAINT; Schema: public; Owner: h3m3r
--

ALTER TABLE ONLY administrator_role
    ADD CONSTRAINT fk_ar_role FOREIGN KEY (role) REFERENCES roles(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1901 (class 2606 OID 21138)
-- Dependencies: 1553 1855 1537
-- Name: fk_billing_address; Type: FK CONSTRAINT; Schema: public; Owner: h3m3r
--

ALTER TABLE ONLY registered_users
    ADD CONSTRAINT fk_billing_address FOREIGN KEY (billing_address) REFERENCES addresses(id) ON UPDATE CASCADE ON DELETE SET NULL;


--
-- TOC entry 1902 (class 2606 OID 21143)
-- Dependencies: 1855 1553 1537
-- Name: fk_delivery_address; Type: FK CONSTRAINT; Schema: public; Owner: h3m3r
--

ALTER TABLE ONLY registered_users
    ADD CONSTRAINT fk_delivery_address FOREIGN KEY (delivery_address) REFERENCES addresses(id) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 1892 (class 2606 OID 21148)
-- Dependencies: 1859 1541 1543
-- Name: fk_delivery_type; Type: FK CONSTRAINT; Schema: public; Owner: h3m3r
--

ALTER TABLE ONLY orders
    ADD CONSTRAINT fk_delivery_type FOREIGN KEY (delivery_type) REFERENCES delivery_types(id) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 1893 (class 2606 OID 21153)
-- Dependencies: 1875 1553 1543
-- Name: fk_o_registered_user; Type: FK CONSTRAINT; Schema: public; Owner: h3m3r
--

ALTER TABLE ONLY orders
    ADD CONSTRAINT fk_o_registered_user FOREIGN KEY (created_by) REFERENCES registered_users(id) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 1895 (class 2606 OID 21158)
-- Dependencies: 1543 1545 1863
-- Name: fk_oli_order; Type: FK CONSTRAINT; Schema: public; Owner: h3m3r
--

ALTER TABLE ONLY order_line_items
    ADD CONSTRAINT fk_oli_order FOREIGN KEY (purchase_order) REFERENCES orders(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1896 (class 2606 OID 21163)
-- Dependencies: 1551 1545 1873
-- Name: fk_oli_product; Type: FK CONSTRAINT; Schema: public; Owner: h3m3r
--

ALTER TABLE ONLY order_line_items
    ADD CONSTRAINT fk_oli_product FOREIGN KEY (product) REFERENCES products(id) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 1894 (class 2606 OID 21168)
-- Dependencies: 1547 1867 1543
-- Name: fk_payment_type; Type: FK CONSTRAINT; Schema: public; Owner: h3m3r
--

ALTER TABLE ONLY orders
    ADD CONSTRAINT fk_payment_type FOREIGN KEY (payment_type) REFERENCES payment_types(id) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 1897 (class 2606 OID 21173)
-- Dependencies: 1549 1540 1857
-- Name: fk_ph_created_by; Type: FK CONSTRAINT; Schema: public; Owner: h3m3r
--

ALTER TABLE ONLY photos
    ADD CONSTRAINT fk_ph_created_by FOREIGN KEY (created_by) REFERENCES administrators(id) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 1898 (class 2606 OID 21178)
-- Dependencies: 1551 1549 1873
-- Name: fk_ph_product; Type: FK CONSTRAINT; Schema: public; Owner: h3m3r
--

ALTER TABLE ONLY photos
    ADD CONSTRAINT fk_ph_product FOREIGN KEY (product) REFERENCES products(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1899 (class 2606 OID 21214)
-- Dependencies: 1551 1885 1560
-- Name: fk_pr_category; Type: FK CONSTRAINT; Schema: public; Owner: h3m3r
--

ALTER TABLE ONLY products
    ADD CONSTRAINT fk_pr_category FOREIGN KEY (category) REFERENCES categories(id);


--
-- TOC entry 1900 (class 2606 OID 21183)
-- Dependencies: 1551 1857 1540
-- Name: fk_pr_created_by; Type: FK CONSTRAINT; Schema: public; Owner: h3m3r
--

ALTER TABLE ONLY products
    ADD CONSTRAINT fk_pr_created_by FOREIGN KEY (created_by) REFERENCES administrators(id) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 1903 (class 2606 OID 21188)
-- Dependencies: 1553 1881 1557
-- Name: fk_ru_user; Type: FK CONSTRAINT; Schema: public; Owner: h3m3r
--

ALTER TABLE ONLY registered_users
    ADD CONSTRAINT fk_ru_user FOREIGN KEY (id) REFERENCES users(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1904 (class 2606 OID 21193)
-- Dependencies: 1873 1554 1551
-- Name: fk_rup_product; Type: FK CONSTRAINT; Schema: public; Owner: h3m3r
--

ALTER TABLE ONLY registered_user_product
    ADD CONSTRAINT fk_rup_product FOREIGN KEY (product) REFERENCES products(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1905 (class 2606 OID 21198)
-- Dependencies: 1553 1554 1875
-- Name: fk_rup_registered_user; Type: FK CONSTRAINT; Schema: public; Owner: h3m3r
--

ALTER TABLE ONLY registered_user_product
    ADD CONSTRAINT fk_rup_registered_user FOREIGN KEY (registered_user) REFERENCES registered_users(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1923 (class 0 OID 0)
-- Dependencies: 3
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2011-01-21 20:15:45 CET

--
-- PostgreSQL database dump complete
--

