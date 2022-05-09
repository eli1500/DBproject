
drop table if exists zakaznik cascade;
create table zakaznik(
    id serial primary key,
    meno varchar,
    priezvisko varchar,
    email varchar,
    prihlasovaciMeno varchar,
    heslo varchar
);

drop table if exists dopyt cascade;
create table dopyt(
    id serial primary key,
    popis_hladania varchar,
    pocet_opakovani integer,
    je_produkt integer
);

drop table if exists dopyt_zakaznika cascade;
create table dopyt_zakaznika(
    id serial primary key,
    zakaznik_id integer references zakaznik(id),
    dopyt_id integer references dopyt(id),
    kedy timestamp
);

drop table if exists adresa cascade;
create table adresa(
    id serial primary key,
    zakaznik_id integer references zakaznik(id),
    ulica varchar,
    PSC varchar,
    mesto varchar,
    krajina varchar
);

drop table if exists produkt cascade;
create table produkt(
    id serial primary key,
    nazov varchar,
    cena numeric
);

drop table if exists objednavka cascade;
create table objednavka(
    id serial primary key,
    zakaznik_id integer references zakaznik(id),
    adresa_id integer references adresa(id),
    cena_spolu numeric,
    stav varchar,
    produkt_id varchar ,
    mnozstvo varchar
);

drop table if exists platba cascade;
create table platba(
    id serial primary key,
    objednavka_id integer references objednavka(id),
    zakaznik_id integer references zakaznik(id),
    suma_platby numeric,
    datum date,
    status_platby varchar,
    metoda_platby varchar
);

drop table if exists atribut cascade;
create table atribut(
    id serial primary key,
    nazov varchar
);


drop table if exists atribut_produktu cascade;
create table atribut_produktu(
    id serial primary key,
    atribut_id integer references atribut(id),
    produkt_id integer references produkt(id),
    hodnota varchar
);


drop table if exists sklad cascade;
create table sklad(
    id serial primary key,
    produkt_id integer references produkt(id),
    pocet_ks integer,
    pocet_rezerv integer
);

drop table if exists kosik cascade;
create table kosik(
    id serial primary key,
    produkt_id integer references produkt(id),
    zakaznik_id integer references zakaznik(id),
    mnozstvo integer,
    kedy timestamp,
    subtotal integer
);





