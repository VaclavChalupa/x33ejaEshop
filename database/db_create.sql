--
-- PostgreSQL database dump
--

-- Started on 2011-01-17 14:50:21 CET

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

--
-- TOC entry 347 (class 2612 OID 16386)
-- Name: plpgsql; Type: PROCEDURAL LANGUAGE; Schema: -; Owner: postgres
--

--CREATE PROCEDURAL LANGUAGE plpgsql;


ALTER PROCEDURAL LANGUAGE plpgsql OWNER TO postgres;

SET search_path = public, pg_catalog;

--
-- TOC entry 315 (class 1247 OID 16482)
-- Dependencies: 3
-- Name: order_state; Type: TYPE; Schema: public; Owner: h3m3r
--



SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 1540 (class 1259 OID 16454)
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
-- TOC entry 1539 (class 1259 OID 16452)
-- Dependencies: 1540 3
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
-- TOC entry 1913 (class 0 OID 0)
-- Dependencies: 1539
-- Name: addresses_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: h3m3r
--

ALTER SEQUENCE addresses_id_seq OWNED BY addresses.id;


--
-- TOC entry 1914 (class 0 OID 0)
-- Dependencies: 1539
-- Name: addresses_id_seq; Type: SEQUENCE SET; Schema: public; Owner: h3m3r
--

SELECT pg_catalog.setval('addresses_id_seq', 1, false);


--
-- TOC entry 1555 (class 1259 OID 16707)
-- Dependencies: 3
-- Name: administrator_role; Type: TABLE; Schema: public; Owner: h3m3r; Tablespace: 
--

CREATE TABLE administrator_role (
    administrator bigint NOT NULL,
    role integer NOT NULL
);


ALTER TABLE public.administrator_role OWNER TO h3m3r;

--
-- TOC entry 1538 (class 1259 OID 16444)
-- Dependencies: 1836 3
-- Name: administrators; Type: TABLE; Schema: public; Owner: h3m3r; Tablespace: 
--

CREATE TABLE administrators (
    id bigint NOT NULL,
    archived boolean DEFAULT false NOT NULL
);


ALTER TABLE public.administrators OWNER TO h3m3r;

--
-- TOC entry 1542 (class 1259 OID 16493)
-- Dependencies: 3
-- Name: delivery_types; Type: TABLE; Schema: public; Owner: h3m3r; Tablespace: 
--

CREATE TABLE delivery_types (
    id integer NOT NULL,
    name character varying(100) NOT NULL,
    description text,
    price money NOT NULL
);


ALTER TABLE public.delivery_types OWNER TO h3m3r;

--
-- TOC entry 1541 (class 1259 OID 16491)
-- Dependencies: 3 1542
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
-- TOC entry 1915 (class 0 OID 0)
-- Dependencies: 1541
-- Name: delivery_types_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: h3m3r
--

ALTER SEQUENCE delivery_types_id_seq OWNED BY delivery_types.id;


--
-- TOC entry 1916 (class 0 OID 0)
-- Dependencies: 1541
-- Name: delivery_types_id_seq; Type: SEQUENCE SET; Schema: public; Owner: h3m3r
--

SELECT pg_catalog.setval('delivery_types_id_seq', 1, false);


--
-- TOC entry 1546 (class 1259 OID 16531)
-- Dependencies: 1841 315 3
-- Name: orders; Type: TABLE; Schema: public; Owner: h3m3r; Tablespace: 
--

CREATE TABLE orders (
    id bigint NOT NULL,
    created timestamp without time zone DEFAULT ('now'::text)::timestamp without time zone NOT NULL,
    description text,
    delivery_type integer NOT NULL,
    payment_type integer NOT NULL,
    state order_state NOT NULL,
    created_by bigint NOT NULL
);


ALTER TABLE public.orders OWNER TO h3m3r;

--
-- TOC entry 1545 (class 1259 OID 16529)
-- Dependencies: 1546 3
-- Name: order_id_seq; Type: SEQUENCE; Schema: public; Owner: h3m3r
--

CREATE SEQUENCE orders_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.orders_id_seq OWNER TO h3m3r;

--
-- TOC entry 1917 (class 0 OID 0)
-- Dependencies: 1545
-- Name: order_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: h3m3r
--

ALTER SEQUENCE orders_id_seq OWNED BY orders.id;


--
-- TOC entry 1918 (class 0 OID 0)
-- Dependencies: 1545
-- Name: order_id_seq; Type: SEQUENCE SET; Schema: public; Owner: h3m3r
--

SELECT pg_catalog.setval('orders_id_seq', 1, false);


--
-- TOC entry 1553 (class 1259 OID 16678)
-- Dependencies: 3
-- Name: order_line_item; Type: TABLE; Schema: public; Owner: h3m3r; Tablespace: 
--

CREATE TABLE order_line_items (
    id bigint NOT NULL,
    count integer NOT NULL,
    purchase_order bigint NOT NULL,
    product bigint NOT NULL
);


ALTER TABLE public.order_line_items OWNER TO h3m3r;

--
-- TOC entry 1552 (class 1259 OID 16676)
-- Dependencies: 1553 3
-- Name: order_line_item_id_seq; Type: SEQUENCE; Schema: public; Owner: h3m3r
--

CREATE SEQUENCE order_line_items_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.order_line_items_id_seq OWNER TO h3m3r;

--
-- TOC entry 1919 (class 0 OID 0)
-- Dependencies: 1552
-- Name: order_line_item_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: h3m3r
--

ALTER SEQUENCE order_line_items_id_seq OWNED BY order_line_items.id;


--
-- TOC entry 1920 (class 0 OID 0)
-- Dependencies: 1552
-- Name: order_line_item_id_seq; Type: SEQUENCE SET; Schema: public; Owner: h3m3r
--

SELECT pg_catalog.setval('order_line_items_id_seq', 1, false);


--
-- TOC entry 1544 (class 1259 OID 16518)
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
-- TOC entry 1543 (class 1259 OID 16516)
-- Dependencies: 1544 3
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
-- TOC entry 1921 (class 0 OID 0)
-- Dependencies: 1543
-- Name: payment_types_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: h3m3r
--

ALTER SEQUENCE payment_types_id_seq OWNED BY payment_types.id;


--
-- TOC entry 1922 (class 0 OID 0)
-- Dependencies: 1543
-- Name: payment_types_id_seq; Type: SEQUENCE SET; Schema: public; Owner: h3m3r
--

SELECT pg_catalog.setval('payment_types_id_seq', 1, false);


--
-- TOC entry 1549 (class 1259 OID 16574)
-- Dependencies: 1846 1847 3
-- Name: photos; Type: TABLE; Schema: public; Owner: h3m3r; Tablespace: 
--

CREATE TABLE photos (
    id bigint NOT NULL,
    name character varying(100) NOT NULL,
    ph_order smallint DEFAULT 0 NOT NULL,
    created timestamp without time zone DEFAULT ('now'::text)::timestamp without time zone NOT NULL,
    product bigint NOT NULL,
    created_by bigint NOT NULL
);


ALTER TABLE public.photos OWNER TO h3m3r;

--
-- TOC entry 1550 (class 1259 OID 16598)
-- Dependencies: 3 1549
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
-- TOC entry 1923 (class 0 OID 0)
-- Dependencies: 1550
-- Name: photos_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: h3m3r
--

ALTER SEQUENCE photos_id_seq OWNED BY photos.id;


--
-- TOC entry 1924 (class 0 OID 0)
-- Dependencies: 1550
-- Name: photos_id_seq; Type: SEQUENCE SET; Schema: public; Owner: h3m3r
--

SELECT pg_catalog.setval('photos_id_seq', 1, false);


--
-- TOC entry 1548 (class 1259 OID 16563)
-- Dependencies: 1843 1844 3 317
-- Name: product; Type: TABLE; Schema: public; Owner: h3m3r; Tablespace: 
--

CREATE TABLE products (
    id bigint NOT NULL,
    name character varying NOT NULL,
    description text NOT NULL,
    price money NOT NULL,
    in_action boolean DEFAULT false NOT NULL,
    action_price money,
    state product_state NOT NULL,
    created timestamp without time zone DEFAULT ('now'::text)::timestamp without time zone NOT NULL,
    created_by bigint NOT NULL
);


ALTER TABLE public.products OWNER TO h3m3r;

--
-- TOC entry 1547 (class 1259 OID 16561)
-- Dependencies: 1548 3
-- Name: product_id_seq; Type: SEQUENCE; Schema: public; Owner: h3m3r
--

CREATE SEQUENCE products_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.products_id_seq OWNER TO h3m3r;

--
-- TOC entry 1925 (class 0 OID 0)
-- Dependencies: 1547
-- Name: product_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: h3m3r
--

ALTER SEQUENCE products_id_seq OWNED BY products.id;


--
-- TOC entry 1926 (class 0 OID 0)
-- Dependencies: 1547
-- Name: product_id_seq; Type: SEQUENCE SET; Schema: public; Owner: h3m3r
--

SELECT pg_catalog.setval('products_id_seq', 1, false);


--
-- TOC entry 1551 (class 1259 OID 16651)
-- Dependencies: 3
-- Name: registered_user; Type: TABLE; Schema: public; Owner: h3m3r; Tablespace: 
--

CREATE TABLE registered_users (
    id bigint NOT NULL,
    delivery_address bigint NOT NULL,
    billing_address bigint
);


ALTER TABLE public.registered_users OWNER TO h3m3r;

--
-- TOC entry 1554 (class 1259 OID 16694)
-- Dependencies: 3
-- Name: registered_user_product; Type: TABLE; Schema: public; Owner: h3m3r; Tablespace: 
--

CREATE TABLE registered_user_product (
    registered_user bigint NOT NULL,
    product bigint NOT NULL
);


ALTER TABLE public.registered_user_product OWNER TO h3m3r;

--
-- TOC entry 1535 (class 1259 OID 16422)
-- Dependencies: 3
-- Name: roles; Type: TABLE; Schema: public; Owner: h3m3r; Tablespace: 
--

CREATE TABLE roles (
    id integer NOT NULL,
    rolename character varying(50) NOT NULL
);


ALTER TABLE public.roles OWNER TO h3m3r;

--
-- TOC entry 1534 (class 1259 OID 16420)
-- Dependencies: 1535 3
-- Name: role_id_seq; Type: SEQUENCE; Schema: public; Owner: h3m3r
--

CREATE SEQUENCE roles_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.roles_id_seq OWNER TO h3m3r;

--
-- TOC entry 1927 (class 0 OID 0)
-- Dependencies: 1534
-- Name: role_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: h3m3r
--

ALTER SEQUENCE roles_id_seq OWNED BY roles.id;


--
-- TOC entry 1928 (class 0 OID 0)
-- Dependencies: 1534
-- Name: role_id_seq; Type: SEQUENCE SET; Schema: public; Owner: h3m3r
--

SELECT pg_catalog.setval('roles_id_seq', 1, false);


--
-- TOC entry 1537 (class 1259 OID 16432)
-- Dependencies: 1835 3
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
-- TOC entry 1536 (class 1259 OID 16430)
-- Dependencies: 3 1537
-- Name: user_id_seq; Type: SEQUENCE; Schema: public; Owner: h3m3r
--

CREATE SEQUENCE users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.users_id_seq OWNER TO h3m3r;

--
-- TOC entry 1929 (class 0 OID 0)
-- Dependencies: 1536
-- Name: user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: h3m3r
--

ALTER SEQUENCE users_id_seq OWNED BY users.id;


--
-- TOC entry 1930 (class 0 OID 0)
-- Dependencies: 1536
-- Name: user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: h3m3r
--

SELECT pg_catalog.setval('users_id_seq', 1, false);


--
-- TOC entry 1837 (class 2604 OID 16457)
-- Dependencies: 1540 1539 1540
-- Name: id; Type: DEFAULT; Schema: public; Owner: h3m3r
--

ALTER TABLE addresses ALTER COLUMN id SET DEFAULT nextval('addresses_id_seq'::regclass);


--
-- TOC entry 1838 (class 2604 OID 16496)
-- Dependencies: 1541 1542 1542
-- Name: id; Type: DEFAULT; Schema: public; Owner: h3m3r
--

ALTER TABLE delivery_types ALTER COLUMN id SET DEFAULT nextval('delivery_types_id_seq'::regclass);


--
-- TOC entry 1848 (class 2604 OID 16681)
-- Dependencies: 1553 1552 1553
-- Name: id; Type: DEFAULT; Schema: public; Owner: h3m3r
--

ALTER TABLE order_line_items ALTER COLUMN id SET DEFAULT nextval('order_line_items_id_seq'::regclass);


--
-- TOC entry 1840 (class 2604 OID 16534)
-- Dependencies: 1546 1545 1546
-- Name: id; Type: DEFAULT; Schema: public; Owner: h3m3r
--

ALTER TABLE orders ALTER COLUMN id SET DEFAULT nextval('orders_id_seq'::regclass);


--
-- TOC entry 1839 (class 2604 OID 16521)
-- Dependencies: 1544 1543 1544
-- Name: id; Type: DEFAULT; Schema: public; Owner: h3m3r
--

ALTER TABLE payment_types ALTER COLUMN id SET DEFAULT nextval('payment_types_id_seq'::regclass);


--
-- TOC entry 1845 (class 2604 OID 16600)
-- Dependencies: 1550 1549
-- Name: id; Type: DEFAULT; Schema: public; Owner: h3m3r
--

ALTER TABLE photos ALTER COLUMN id SET DEFAULT nextval('photos_id_seq'::regclass);


--
-- TOC entry 1842 (class 2604 OID 16566)
-- Dependencies: 1547 1548 1548
-- Name: id; Type: DEFAULT; Schema: public; Owner: h3m3r
--

ALTER TABLE products ALTER COLUMN id SET DEFAULT nextval('products_id_seq'::regclass);


--
-- TOC entry 1833 (class 2604 OID 16425)
-- Dependencies: 1534 1535 1535
-- Name: id; Type: DEFAULT; Schema: public; Owner: h3m3r
--

ALTER TABLE roles ALTER COLUMN id SET DEFAULT nextval('roles_id_seq'::regclass);


--
-- TOC entry 1834 (class 2604 OID 16435)
-- Dependencies: 1537 1536 1537
-- Name: id; Type: DEFAULT; Schema: public; Owner: h3m3r
--

ALTER TABLE users ALTER COLUMN id SET DEFAULT nextval('users_id_seq'::regclass);



ALTER TABLE ONLY addresses
    ADD CONSTRAINT pk_address PRIMARY KEY (id);


--
-- TOC entry 1858 (class 2606 OID 16624)
-- Dependencies: 1538 1538
-- Name: pk_administrator; Type: CONSTRAINT; Schema: public; Owner: h3m3r; Tablespace: 
--

ALTER TABLE ONLY administrators
    ADD CONSTRAINT pk_administrator PRIMARY KEY (id);


--
-- TOC entry 1862 (class 2606 OID 16501)
-- Dependencies: 1542 1542
-- Name: pk_delivery_type; Type: CONSTRAINT; Schema: public; Owner: h3m3r; Tablespace: 
--

ALTER TABLE ONLY delivery_types
    ADD CONSTRAINT pk_delivery_type PRIMARY KEY (id);


--
-- TOC entry 1870 (class 2606 OID 16540)
-- Dependencies: 1546 1546
-- Name: pk_order; Type: CONSTRAINT; Schema: public; Owner: h3m3r; Tablespace: 
--

ALTER TABLE ONLY orders
    ADD CONSTRAINT pk_order PRIMARY KEY (id);


--
-- TOC entry 1878 (class 2606 OID 16683)
-- Dependencies: 1553 1553
-- Name: pk_order_line_item; Type: CONSTRAINT; Schema: public; Owner: h3m3r; Tablespace: 
--

ALTER TABLE ONLY order_line_items
    ADD CONSTRAINT pk_order_line_item PRIMARY KEY (id);


--
-- TOC entry 1866 (class 2606 OID 16526)
-- Dependencies: 1544 1544
-- Name: pk_payment_type; Type: CONSTRAINT; Schema: public; Owner: h3m3r; Tablespace: 
--

ALTER TABLE ONLY payment_types
    ADD CONSTRAINT pk_payment_type PRIMARY KEY (id);


--
-- TOC entry 1874 (class 2606 OID 16613)
-- Dependencies: 1549 1549
-- Name: pk_photo; Type: CONSTRAINT; Schema: public; Owner: h3m3r; Tablespace: 
--

ALTER TABLE ONLY photos
    ADD CONSTRAINT pk_photo PRIMARY KEY (id);


--
-- TOC entry 1872 (class 2606 OID 16573)
-- Dependencies: 1548 1548
-- Name: pk_product; Type: CONSTRAINT; Schema: public; Owner: h3m3r; Tablespace: 
--

ALTER TABLE ONLY products
    ADD CONSTRAINT pk_product PRIMARY KEY (id);


--
-- TOC entry 1876 (class 2606 OID 16655)
-- Dependencies: 1551 1551
-- Name: pk_registered_user; Type: CONSTRAINT; Schema: public; Owner: h3m3r; Tablespace: 
--

ALTER TABLE ONLY registered_users
    ADD CONSTRAINT pk_registered_user PRIMARY KEY (id);


--
-- TOC entry 1850 (class 2606 OID 16427)
-- Dependencies: 1535 1535
-- Name: pk_role; Type: CONSTRAINT; Schema: public; Owner: h3m3r; Tablespace: 
--

ALTER TABLE ONLY roles
    ADD CONSTRAINT pk_role PRIMARY KEY (id);


--
-- TOC entry 1854 (class 2606 OID 16441)
-- Dependencies: 1537 1537
-- Name: pk_user; Type: CONSTRAINT; Schema: public; Owner: h3m3r; Tablespace: 
--

ALTER TABLE ONLY users
    ADD CONSTRAINT pk_user PRIMARY KEY (id);


--
-- TOC entry 1864 (class 2606 OID 16503)
-- Dependencies: 1542 1542
-- Name: uq_name; Type: CONSTRAINT; Schema: public; Owner: h3m3r; Tablespace: 
--

ALTER TABLE ONLY delivery_types
    ADD CONSTRAINT uq_name UNIQUE (name);


--
-- TOC entry 1868 (class 2606 OID 16528)
-- Dependencies: 1544 1544
-- Name: uq_pt_name; Type: CONSTRAINT; Schema: public; Owner: h3m3r; Tablespace: 
--

ALTER TABLE ONLY payment_types
    ADD CONSTRAINT uq_pt_name UNIQUE (name);


--
-- TOC entry 1852 (class 2606 OID 16429)
-- Dependencies: 1535 1535
-- Name: uq_rolename; Type: CONSTRAINT; Schema: public; Owner: h3m3r; Tablespace: 
--

ALTER TABLE ONLY roles
    ADD CONSTRAINT uq_rolename UNIQUE (rolename);


--
-- TOC entry 1856 (class 2606 OID 16443)
-- Dependencies: 1537 1537
-- Name: uq_username; Type: CONSTRAINT; Schema: public; Owner: h3m3r; Tablespace: 
--

ALTER TABLE ONLY users
    ADD CONSTRAINT uq_username UNIQUE (username);


--
-- TOC entry 1879 (class 2606 OID 16625)
-- Dependencies: 1537 1538 1853
-- Name: fk_ad_user; Type: FK CONSTRAINT; Schema: public; Owner: h3m3r
--

ALTER TABLE ONLY administrators
    ADD CONSTRAINT fk_ad_user FOREIGN KEY (id) REFERENCES users(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1893 (class 2606 OID 16710)
-- Dependencies: 1857 1538 1555
-- Name: fk_ar_administrator; Type: FK CONSTRAINT; Schema: public; Owner: h3m3r
--

ALTER TABLE ONLY administrator_role
    ADD CONSTRAINT fk_ar_administrator FOREIGN KEY (administrator) REFERENCES administrators(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1894 (class 2606 OID 16715)
-- Dependencies: 1849 1535 1555
-- Name: fk_ar_role; Type: FK CONSTRAINT; Schema: public; Owner: h3m3r
--

ALTER TABLE ONLY administrator_role
    ADD CONSTRAINT fk_ar_role FOREIGN KEY (role) REFERENCES roles(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1888 (class 2606 OID 16666)
-- Dependencies: 1551 1859 1540
-- Name: fk_billing_address; Type: FK CONSTRAINT; Schema: public; Owner: h3m3r
--

ALTER TABLE ONLY registered_users
    ADD CONSTRAINT fk_billing_address FOREIGN KEY (billing_address) REFERENCES addresses(id) ON UPDATE CASCADE ON DELETE SET NULL;


--
-- TOC entry 1887 (class 2606 OID 16661)
-- Dependencies: 1859 1551 1540
-- Name: fk_delivery_address; Type: FK CONSTRAINT; Schema: public; Owner: h3m3r
--

ALTER TABLE ONLY registered_users
    ADD CONSTRAINT fk_delivery_address FOREIGN KEY (delivery_address) REFERENCES addresses(id) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 1880 (class 2606 OID 16541)
-- Dependencies: 1546 1542 1861
-- Name: fk_delivery_type; Type: FK CONSTRAINT; Schema: public; Owner: h3m3r
--

ALTER TABLE ONLY orders
    ADD CONSTRAINT fk_delivery_type FOREIGN KEY (delivery_type) REFERENCES delivery_types(id) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 1882 (class 2606 OID 16671)
-- Dependencies: 1546 1875 1551
-- Name: fk_o_registered_user; Type: FK CONSTRAINT; Schema: public; Owner: h3m3r
--

ALTER TABLE ONLY orders
    ADD CONSTRAINT fk_o_registered_user FOREIGN KEY (created_by) REFERENCES registered_users(id) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 1889 (class 2606 OID 16684)
-- Dependencies: 1546 1553 1869
-- Name: fk_oli_order; Type: FK CONSTRAINT; Schema: public; Owner: h3m3r
--

ALTER TABLE ONLY order_line_items
    ADD CONSTRAINT fk_oli_order FOREIGN KEY (purchase_order) REFERENCES orders(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1890 (class 2606 OID 16689)
-- Dependencies: 1553 1871 1548
-- Name: fk_oli_product; Type: FK CONSTRAINT; Schema: public; Owner: h3m3r
--

ALTER TABLE ONLY order_line_items
    ADD CONSTRAINT fk_oli_product FOREIGN KEY (product) REFERENCES products(id) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 1881 (class 2606 OID 16546)
-- Dependencies: 1865 1546 1544
-- Name: fk_payment_type; Type: FK CONSTRAINT; Schema: public; Owner: h3m3r
--

ALTER TABLE ONLY orders
    ADD CONSTRAINT fk_payment_type FOREIGN KEY (payment_type) REFERENCES payment_types(id) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 1885 (class 2606 OID 16631)
-- Dependencies: 1857 1549 1538
-- Name: fk_ph_created_by; Type: FK CONSTRAINT; Schema: public; Owner: h3m3r
--

ALTER TABLE ONLY photos
    ADD CONSTRAINT fk_ph_created_by FOREIGN KEY (created_by) REFERENCES administrators(id) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 1884 (class 2606 OID 16614)
-- Dependencies: 1549 1548 1871
-- Name: fk_ph_product; Type: FK CONSTRAINT; Schema: public; Owner: h3m3r
--

ALTER TABLE ONLY photos
    ADD CONSTRAINT fk_ph_product FOREIGN KEY (product) REFERENCES products(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1883 (class 2606 OID 16636)
-- Dependencies: 1857 1538 1548
-- Name: fk_pr_created_by; Type: FK CONSTRAINT; Schema: public; Owner: h3m3r
--

ALTER TABLE ONLY products
    ADD CONSTRAINT fk_pr_created_by FOREIGN KEY (created_by) REFERENCES administrators(id) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 1886 (class 2606 OID 16656)
-- Dependencies: 1551 1537 1853
-- Name: fk_ru_user; Type: FK CONSTRAINT; Schema: public; Owner: h3m3r
--

ALTER TABLE ONLY registered_users
    ADD CONSTRAINT fk_ru_user FOREIGN KEY (id) REFERENCES users(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1892 (class 2606 OID 16702)
-- Dependencies: 1554 1871 1548
-- Name: fk_rup_product; Type: FK CONSTRAINT; Schema: public; Owner: h3m3r
--

ALTER TABLE ONLY registered_user_product
    ADD CONSTRAINT fk_rup_product FOREIGN KEY (product) REFERENCES products(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1891 (class 2606 OID 16697)
-- Dependencies: 1875 1551 1554
-- Name: fk_rup_registered_user; Type: FK CONSTRAINT; Schema: public; Owner: h3m3r
--

ALTER TABLE ONLY registered_user_product
    ADD CONSTRAINT fk_rup_registered_user FOREIGN KEY (registered_user) REFERENCES registered_users(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1912 (class 0 OID 0)
-- Dependencies: 3
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

---REVOKE ALL ON SCHEMA public FROM PUBLIC;
---REVOKE ALL ON SCHEMA public FROM postgres;
---GRANT ALL ON SCHEMA public TO postgres;
---GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2011-01-17 14:50:21 CET

--
-- PostgreSQL database dump complete
--

