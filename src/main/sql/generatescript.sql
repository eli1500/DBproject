truncate table zakaznik, dopyt, dopyt_zakaznika, adresa, objednavka, platba, atribut, produkt, atribut_produktu, sklad, kosik restart identity cascade;

--krstne mena
drop table if exists first_names cascade;
create table first_names
(
    first_name varchar
);

insert into first_names(first_name)
values ('Adam'), ('Alojz'), ('Ctibor'), ('Zoltan'), ('Karol'),
	('Lenka'), ('Anna'), ('Hana'), ('Radoslava'), ('Mariana'),
	('Robert'), ('Jozef'), ('Robert'), ('Kamil'), ('Dominik'),
	('Bartolomej'), ('Medard'), ('Alexander'), ('Bohuš'), ('Emil'),
	('Marek'), ('Zlatica'), ('Alojz'), ('Viktor'), ('Benjamín'),
	('Dávid'), ('Jákob'), ('Filoména'), ('Goliáš'), ('Rút');

--priezviska
drop table if exists last_names cascade;
create table last_names
(
    last_name varchar
);

insert into last_names(last_name)
values ('Pukas'), ('Jenčik'), ('Ciglian'), ('Stieranka'), ('Silagy'),
	('Eštok'), ('Balog'), ('Hudec'), ('Šomloj'), ('Meran'),
	('Kromlík'), ('Sudec'), ('Banník'), ('Kopeš'), ('Drobík'),
	('Rudaš'), ('Medard'), ('Bendik'), ('Cidofer'), ('Ferenc'),
	('Kremík'), ('Šuhaj'), ('Mottol'), ('Dobran'), ('Gorov'),
	('Vandor'), ('Jakubík'), ('Vejak'), ('Horváth'), ('Poltár');

--emaily
drop table if exists emails cascade;
create table emails
(
    email varchar
);

insert into emails(email)
values ('chance@me.com'), ('budinger@gmail.com'), ('portele@me.com'), ('mrobshaw@me.com'), ('sowsy@verizon.net'),
	('Bboris@azet.sk'), ('kodeman@verizon.net@gmail.com'), ('marnanel@att.net'), ('valdez@me.com'), ('rdagna@sbcglobal.net'),
	('donev@hotmail.com'), ('webdragon@comcast.net'), ('crowl@verizon.net'), ('dunstan@yahoo.ca'), ('tbusch@yahoo.ca');

--prihlasovacie mena


drop table if exists usernames cascade;
create table usernames
(
    username varchar
);

insert into usernames(username)
values ('Cookie Dough'), ('Rufio'), ('Queenie'), ('Chickie'), ('Chumgy'),
	('Cindy Lou Who'), ('Cheesestick'), ('Smudge'), ('Brutus'), ('Senior'),
	('Butterfinger'), ('Herp Derp'), ('Bandit'), ('Cheesestick'), ('General'),
	('Cutie'), ('Amore'), ('CHeddar'), ('Amiga'), ('Thunder Thighs'),
	('Braveheart'), ('Cotton'), ('Braniac'), ('Pecan'), ('Ace123'),
	('Silly Gilly'), ('Ms. Congeniality'), ('Blimpie'), ('Goonie'), ('Dottie');

--hesla dlzky 6 z upper aj lowercase plus cisla
drop table if exists passwords cascade;
create table passwords
(
    pass varchar
);

insert into passwords(pass)
values ('JR-8j1'), ('91@Ct1'), ('5fb2mr'), ('a96J@2'), ('DwOo4V'),
	('1LXXG2'), ('p4UK+8'), ('KHEmTe'), ('9=3nlr'), ('K~&Re^'),
	('xTO4x@'), ('MF0jCF'), ('E113p@'), ('uO2#+6'), ('jR4$X7'),
	('CI49iy'), ('bfD!^6'), ('S74492'), ('9OJAA1'), ('53^1Hp'),
	('RO81G4'), ('en3SuO'), ('9PV_WW'), ('1rr83f'), ('5fw1c2'),
	('NU+E82'), ('8t@p&a'), ('7Nx17s'), ('8fWO~5'), ('8NA5#s');

--popis hladania
drop table if exists popisy cascade;
create table popisy
(
    popis varchar
);

insert into popisy(popis)
values ('Farba'), ('Velkost'), ('Dizajn'), ('Vaha'), ('Chut'),
	('Vona'), ('Material'), ('Krajina vyroby'), ('Obal'),('Kategoria'),
	('E-kniha'), ('Podlozka pod mysku'), ('HDMI kabel'), ('Lenovo C5300'), ('Sampon'),
	('Odmerkove lyzice'), ('Zaclona'), ('Playstation'), ('Presteiranie na stol'), ('Okno'),('Dvere'),
	('Mixazny pult'), ('Mixer'), ('Waflovac'), ('Ventilator'), ('Uterak'),('Damske pyzamo'),
	('Starter na nabijanie'), ('Olej'), ('Baby Anabel'), ('Vonna sviecka'), ('Stvorcekovany zosit'),('Kuracie sote'),
	('biela'), ('zlta'), ('modra'), ('cervena'), ('cierna'),
	('1080x300'), ('10x300'), ('40x30'),
	('kvetovany'),('priezracny'),('holograficky'),
	('200g'), ('1,3kg'), ('500g'), ('134kg'), ('2,5t'),
	('cerstva'), ('orieskova'), ('vanilkova'),
	 ('bavlna'), ('elasten'),('drevo'), ('kov'), ('plast'),
	 ('Cina'), ('Polsko'),('Ceska republika'), ('Banglades'), ('Korea'),
	 ('textilny'), ('pevna vazba'),('makka vazba'),
	 ('damske'), ('panske'), ('detstke'), ('dekoracie'), ('interier'), ('exterier'), ('domace zvierata');



-- ulice
drop table if exists ulice cascade;
create table ulice
(
    u varchar
);

insert into ulice (u)
values	('Main Street'), ('Church Street'), ('Main Street North'), ('Main Street South'), ('Elm Street'),
	('High Street'), ('Washington Street'), ('Main Street West'), ('Main Street East'), ('Park Avenue'),
	('Walnut Street'), ('2nd Street'), ('Chestnut Street'), ('Broad Street'), ('Maple Avenue'),
	('Maple Street'), ('Center Street'), ('Oak Street'), ('Pine Street'), ('River Road');

-- mesta
drop table if exists mesta cascade;
create table mesta
(
    m varchar
);

insert into mesta (m)
values	('Santiago'), ('Kolkota'), ('Minsk'), ('Shanghai'), ('Johannesburg'),
	('Lagos'), ('Hanoi'), ('Alexandria'), ('Palembang'), ('Tangshan'),
	('Montreal'), ('Birmingham'), ('Lima'), ('Dubai'), ('Tunis'),
	('Berlin'), ('Osaka'), ('Prague'), ('Mexico City'), ('Peking');


-- krajiny
drop table if exists krajiny cascade;
create table krajiny
(
    k varchar
);

insert into krajiny (k)
values	('Namibia'), ('Dominican Republic'), ('Cyprus'), ('Kyrgyzstan'), ('Fiji'),
	('New Zealand'), ('Norway'), ('Peru'), ('Zimbabwe'), ('Iraq'),
	('Mali'), ('Guinea-Bissau'), ('Kazakhstan'), ('Tanzania'), ('Sri Lanka'),
	('San Marino'), ('Lebanon'), ('Guatemala'), ('Macedonia'), ('Malta');


-- nazvy atributov
drop table if exists atributy cascade;
create table atributy
(
    a varchar
);

insert into atributy (a)
values	('Farba'), ('Velkost'), ('Dizajn'), ('Vaha'), ('Chut'),
	('Vona'), ('Material'), ('Krajina vyroby'), ('Obal'),('Kategoria');

-- nazvy produktov
drop table if exists produkty cascade;
create table produkty
(
    p varchar
);

insert into produkty (p)
values	('E-kniha'), ('Podlozka pod mysku'), ('HDMI kabel'), ('Lenovo C5300'), ('Sampon'),
	('Odmerkove lyzice'), ('Zaclona'), ('Playstation'), ('Presteiranie na stol'), ('Okno'),('Dvere'),
	('Mixazny pult'), ('Mixer'), ('Waflovac'), ('Ventilator'), ('Uterak'),('Damske pyzamo'),
	('Starter na nabijanie'), ('Olej'), ('Baby Anabel'), ('Vonna sviecka'), ('Stvorcekovany zosit'),('Kuracie sote');

-- nazvy hodnot atributov produktov
drop table if exists atributyProduktov cascade;
create table atributyProduktov
(
    ap varchar
);

insert into atributyProduktov (ap)
values	('biela'), ('zlta'), ('modra'), ('cervena'), ('cierna'),
	('1080x300'), ('10x300'), ('40x30'),
	('kvetovany'),('priezracny'),('holograficky'),
	('200g'), ('1,3kg'), ('500g'), ('134kg'), ('2,5t'),
	('cerstva'), ('orieskova'), ('vanilkova'),
	 ('bavlna'), ('elasten'),('drevo'), ('kov'), ('plast'),
	 ('Cina'), ('Polsko'),('Ceska republika'), ('Banglades'), ('Korea'),
	 ('textilny'), ('pevna vazba'),('makka vazba'),
	 ('damske'), ('panske'), ('detstke'), ('dekoracie'), ('interier'), ('exterier'), ('domace zvierata');





--funkcie
create or replace function random_first_name() returns varchar language sql as
$$
select first_name from first_names order by random() limit 1
$$;

create or replace function random_last_name() returns varchar language sql as
$$
select last_name from last_names order by random() limit 1
$$;

create or replace function random_email() returns varchar language sql as
$$
select email from emails order by random() limit 1
$$;

create or replace function random_username() returns varchar language sql as
$$
select username from usernames order by random() limit 1
$$;

create or replace function random_passwords() returns varchar language sql as
$$
select pass from passwords order by random() limit 1
$$;

create or replace function random_popis() returns varchar language sql as
$$
select popis from popisy order by random() limit 1
$$;

create or replace function random_ulica() returns varchar language sql as
$$
select u from ulice order by random() limit 1
$$;

create or replace function random_mesto() returns varchar language sql as
$$
select m from mesta order by random() limit 1
$$;

create or replace function random_krajina() returns varchar language sql as
$$
select k from krajiny order by random() limit 1
$$;

create or replace function random_atribut() returns varchar language sql as
$$
select a from atributy order by random() limit 1
$$;

create or replace function random_produkt() returns varchar language sql as
$$
select p from produkty order by random() limit 1
$$;

create or replace function random_ap() returns varchar language sql as
$$
select ap from atributyProduktov order by random() limit 1
$$;

--hlavne tabulky
--zakaznici

insert into zakaznik(meno, priezvisko, email, prihlasovaciMeno,heslo)
 select  random_first_name(),
        random_last_name(),
        random_email(),
        random_username(),
        random_passwords()
from generate_series(1, 100000) as seq(i);


insert into dopyt(popis_hladania, pocet_opakovani, je_produkt)
select  random_popis(),
        floor(random() * 100 + 1 ) as pocet_opakovani,
        (CASE
                WHEN random() < 0.5  THEN  1
                ELSE 0
            END) as je_produkt

from generate_series(1,10000) as seq(i);



with data as (
    select  s.i,
            random_zakaznik_id() as zakaznik_id,
            s.id as dopyt_id,
            timestamp '2015-01-01 00:00:00' +
       random() * (timestamp '2022-01-01 00:00:00' -
                   timestamp '2015-01-01 00:00:00') as kedy
           -- current_timestamp as kedy

    from (generate_series(1, 200) as seq(i)
             cross join lateral (select seq.i, * from dopyt order by random() limit 1) as s)
)
insert into dopyt_zakaznika(zakaznik_id, dopyt_id, kedy)
select zakaznik_id, dopyt_id, kedy from data;



--generovanie random idciek z konkretnych tabuliek
create or replace function random_zakaznik_id() returns integer language sql as
$$
select id from zakaznik   order by random() limit 1
$$;

create or replace function random_dopyt_id() returns integer language sql as
$$
select id from dopyt   order by random() limit 1
$$;

create or replace function random_adresa_id() returns integer language sql as
$$
select id from adresa   order by random() limit 1
$$;

create or replace function random_produkt_id() returns integer language sql as
$$
select   id from produkt  order by random()  limit 1
$$;

create or replace function random_atribut_id() returns integer language sql as
$$
select id from atribut   order by random() limit 1
$$;



insert into adresa(zakaznik_id, ulica, PSC, mesto, krajina)
select random_zakaznik_id() as zakaznik_id,
       random_ulica() || ' ' || floor(random() * 100) + 1  as ulica,
       floor(100 + random() * 899) || ' ' || floor(10 + random() * 89) ,
       random_mesto() as mesto,
       random_krajina() as krajina
from generate_series(1,10000) as seq(i);

--prvy sposob cez with
with data as (
    select  s.i,
            s.id as objednavka_id,
            s.zakaznik_id as zakaznik_id,
            s.cena_spolu  as suma_platby,
            date '2016-01-01' + floor(random()*300)::integer as datum,
            (CASE
                WHEN random() < 0.2  THEN 'spracovana'
                WHEN random() < 0.4 THEN 'cakajuca na spracovanie'
                WHEN random() < 0.8 THEN 'dokoncena'
                ELSE 'stornovana'
            END) as status_platby,
         (CASE
                WHEN random() < 0.2  THEN 'PayPal'
                WHEN random() < 0.4 THEN 'Amazon Pay'
                WHEN random() < 0.6 THEN 'Google Pay'
                WHEN random() < 0.8 THEN 'Apple Pay'
                WHEN random() < 0.9 THEN 'Bankovy prevod'
                ELSE 'Darcekova poukazka'
          END) as metoda_platby
    from (generate_series(1, 10000) as seq(i)
             cross join lateral (select seq.i, * from objednavka order by random() limit 1) as s)
)

insert into platba(objednavka_id, zakaznik_id, suma_platby, datum, status_platby, metoda_platby)
select objednavka_id, zakaznik_id, suma_platby, datum, status_platby, metoda_platby from data;



insert into atribut(nazov)
select random_atribut() as nazov
from generate_series(1,1000) as seq(i);

insert into produkt(nazov, cena)
select random_produkt() as nazov,
floor(1 + random() * 100)::int as cena

from generate_series(1,2000) as seq(i);


with dataPRESKLAD as (
    select  s.i,
            s.id as produkt_id,  --s.id as produkt_id  from sample(1, 50, 50)
            floor(random() * 1000 + 1 ) as pocet_ks,
            floor(random() * 50 + 1 ) as pocet_rezerv

    from (generate_series(1, 1) as seq(i)
             cross join lateral (select   distinct on (produkt.id) produkt.id, seq.i from produkt order by produkt.id ) as s)
)

insert into sklad(produkt_id, pocet_ks, pocet_rezerv)
select produkt_id, pocet_ks, pocet_rezerv from dataPRESKLAD;


insert into atribut_produktu(atribut_id, produkt_id, hodnota)
select a.id, p.id, random_ap() as hodnota
from   atribut a
join   produkt p on random() >= 0.99;


create index apIDX on atribut_produktu(produkt_id, atribut_id, hodnota);
create index kosikIDX on kosik(produkt_id, zakaznik_id);
create index objIDX on objednavka(zakaznik_id);
create index produktIDX on produkt(nazov);
create index atribIDX on atribut(nazov);
create index skadIDX on sklad(produkt_id);
create index zakIDX on zakaznik(prihlasovacimeno); --ten asi netreba

drop index produktIDX;

--select * from produkt;
-- select * from
--   produkt as p
-- join atribut_produktu as ap on ap.produkt_id = p.id
-- join atribut a on ap.atribut_id = a.id
--  where to_tsvector(p.nazov) @@ to_tsquery('starter ') ;

--select * from atribut_produktu where  to_tsvector(hodnota) @@ to_tsquery('zlta');

--select * from dopyt_zakaznika;
--select * from produkt as p
-- join atribut_produktu as ap on ap.produkt_id = p.id
-- join atribut a on ap.atribut_id = a.id;


insert into kosik(zakaznik_id, produkt_id,mnozstvo, kedy,subtotal )
select random_zakaznik_id() as zakaznik_id,
       random_produkt_id()  as produkt_id,
       floor(1 + random() * 100)::int as mnozstvo,
       (timestamp '2014-01-10 20:00:00' +
       random() * (timestamp '2022-01-01 20:00:00' -
                   timestamp '2020-01-01 10:00:00')) as kedy,
       --current_timestamp + floor(random()*300)::timestamp as kedy,
        floor(1 + random() * 900)::int as subtotal

from generate_series(1,100) as seq(i);



with data as (
    select  s.i,
            s.zakaznik_id as zakaznik_id,
            random_adresa_id() as adresa_id,
            floor(1 + random() * 900)::int as cena_spolu,
            (case
        when random() < 0.2  then 'vytvorena'
        when random() < 0.4 then 'zaplatena'
        when random() < 0.6 then 'expedovana'
        else 'zrusena'
       end) as stav,
          (random_produkt_id()::varchar ||','::varchar ||  random_produkt_id()::varchar) as produkt_id,
           ( s.mnozstvo::varchar || ',' || floor(1 + random() * 40)::varchar) as mnozstvo

    from (generate_series(1, 1000) as seq(i)
             cross join lateral (select seq.i, * from kosik order by random() limit 1) as s)
)
insert into objednavka(zakaznik_id, adresa_id,cena_spolu, stav, produkt_id, mnozstvo)
select zakaznik_id, adresa_id,cena_spolu, stav, produkt_id, mnozstvo from data;


--drop table if exists stat1 cascade;
--create table stat1(
--    id serial primary key,
--    pp integer,
--    numberOfAtributtes integer
--);
--statistika??

--with data as(
--    (select k.produkt_id as pp, count( ap.atribut_id) as numberOfAtributtes from atribut_produktu as ap
--    JOIN kosik k ON k.produkt_id = ap.produkt_id
--    GROUP BY  k.produkt_id))
--insert into stat1(pp, numberOfAtributtes)
--select pp , numberOfAtributtes from data;
--
--select * from stat1;
--select count(numberOfAtributtes) as NumberOfSoldProducts, numberOfAtributtes
--from stat1
--group by numberOfAtributtes;


 WITH cte AS (
    SELECT
        to_char(kedy, 'YYYY-MM') AS tochar1,
        count(je_produkt) AS count_vsetky,
        count(je_produkt) FILTER (WHERE je_produkt = 0) AS count_0
    FROM
        dopyt as d
        JOIN dopyt_zakaznika dz ON d.id = dz.dopyt_id
    WHERE
        kedy >= '2018-01-01 00:00' at time zone 'UTC'
        AND kedy <= '2022-12-31 23:59' at time zone 'UTC'
    GROUP BY
        1
),
cte2 (
    yearmonth
) AS (
    SELECT
        to_char(g, 'YYYY-MM')
    FROM
        generate_series('2018-01-01', '2022-12-31', interval '1 month') g
)
SELECT
    yearmonth,
    cte.*,
    round(cte.count_0::numeric / count_vsetky, 2)
FROM
    cte
    RIGHT JOIN cte2 ON cte.tochar1 = yearmonth;

-----------------------------------------------------

with cte as
(select
    to_char(kedy,'YYYY-MM') as existujeZaznam,
    count(je_produkt) as count_all,
    count(je_produkt) filter ( where je_produkt = 0 ) as count_0
    from dopyt join dopyt_zakaznika dz on dopyt.id = dz.dopyt_id
where kedy >= '2021-01-01 00:00' at time zone 'UTC'
 and  kedy <= '2022-12-31 23:59' at time zone 'UTC'
group by 1), cte2(yearmonth) as
(select to_char(g,'YYYY-MM') from generate_series('2021-01-01', '2022-12-31', interval '1 month')  g)
select yearmonth,
        COALESCE(cte.existujeZaznam::varchar,'null') as existujezaznam,
       COALESCE (cte.count_all::varchar, 'null') as pocetProduktov,
      COALESCE( cte.count_0::varchar, 'null') as pocetNeproduktov,
 COALESCE(round(cte.count_0::numeric/count_all,2)::varchar, 'null') as pomer
    from cte right join cte2 on cte.existujeZaznam = yearmonth;


select
    to_char(kedy,'YYYY-MM') as existujeZaznam,
    count(je_produkt) as count_all,
    count(je_produkt) filter ( where je_produkt = 0 ) as count_0
    from dopyt join dopyt_zakaznika dz on dopyt.id = dz.dopyt_id
    group by 1;

select * from atribut_produktu where produkt_id = 2001;
select * from kosik;
 --nakoniec zmazem nepotrebne tabulky

drop TABLE first_names, last_names, usernames, emails, ulice, passwords, popisy, mesta, krajiny, atributy, produkty, atributyProduktov  cascade;
drop function random_first_name();
drop function random_last_name();
drop function random_email();
drop function random_username();
drop function random_passwords();
drop function random_popis();
drop function random_ulica();
drop function random_mesto();
drop function random_krajina();
drop function random_atribut();
drop function random_produkt();
drop function random_ap();
drop function random_zakaznik_id();
drop function random_dopyt_id();
drop function random_produkt_id();
drop function random_atribut_id();
drop function random_adresa_id();
drop index zakIND;

$$;

--end




--dalsi sposob ale rovnake idcka dava
--insert into platba(objednavka_id, zakaznik_id, suma_platby, datum, status_platby, metoda_platby)
--select (select o.id from objednavka as o order by random() limit 1) as objednavka_id,
--       (select o.zakaznik_id from objednavka as o order by random() limit 1 ) as zakaznik_id,
--       (select o.cena_spolu from objednavka as o order by random() limit 1) as suma_platby,
--       (date '2016-01-01' + floor(random()*300)::integer) as datum,
--      (CASE
--        WHEN random() < 0.2  THEN 'spracovana'
--        WHEN random() < 0.4 THEN 'cakajuca na spracovanie'
--        WHEN random() < 0.8 THEN 'dokoncena'
--        ELSE 'stornovana'
--      END) as status_platby,
--     (CASE
--        WHEN random() < 0.2  THEN 'PayPal'
--        WHEN random() < 0.4 THEN 'Amazon Pay'
--        WHEN random() < 0.6 THEN 'Google Pay'
--        WHEN random() < 0.8 THEN 'Apple Pay'
--        WHEN random() < 0.9 THEN 'Bankovy prevod'
--        ELSE 'Darcekova poukazka'
--      END) as metoda_platby
--from generate_series(1,1000) as seq(i);








