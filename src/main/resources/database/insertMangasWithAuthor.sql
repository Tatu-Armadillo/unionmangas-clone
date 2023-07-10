insert into authors (name, pseudonym, age, birthdate) 
values('Shunsuke Hibaru', '', 35, '1980-07-23');

insert into mangas 
(main_title, alternatives_title, volume_quantity, status, rating, release_date, last_update, link_image, description) 
values
('Usami-san wa Kamawaretai!u', 'うさみさんは構われたい!', 4, 'ATIVO', 8.05, '2020-09-17', '2023-07-10', 'https://static3.mangalivre.net/capas/qKBV5DsjyjXTzHePt79l4g/10809/5f63fabf79fc7external_cover.jpg', 'Usami Nagisa é uma delinquente que sofre de uma suposta doença rara que faz com que sua saúde diminua se ela se sentir sozinha. Sewa Natsuhito, o menino que senta ao lado dela na aula percebe isso e lhe faz companhia, mas a única maneira de Usami acreditar que ela pode curar sua "doença" é fazendo muitos amigos, um processo que ela pode achar muito difícil devido à sua agressividade aparência e personalidade.'),
('Suki x Suki', 'Love x Clear, 好き×透き', 2, 'FINALIZADO', 7.86, '2018-01-02', '2020-07-11', 'https://static3.mangalivre.net/capas/UnhbzGzjxg-zSBAjVDA-dg/6961/capa.jpg', 'Touka Aizuki é uma garota que consegue ficar invisível, e ninguém consegue vê-la, exceto por uma pessoa, Ryoutarou Mimori. Mesmo ela estando fisicamente invisível, seu amor é completamente visível'),
('Re:LIVE', 'リ：ライブ', 1, 'FINALIZADO', 7.13, '2021-10-21', '2021-10-21', 'https://static3.mangalivre.net/capas/sHMFG3wNGYPu9zln5b1BhQ/1032/capa.jpg', 'No futuro, o governo "recicla" os crimonosos perigosos, ao invez de matá-los. As memórias são zeradas, e a personalidades são substituidas, e depois são mandados de volta para a vida como uma nova pessoa. Kou volta para a casa após passar por este processo, por ter cometido um assassinato. Ele tem apenas uma agenda que pode usar para se lembrar dos detalhes da vida dele, e dos relacionamentos. Contudo, os colegas de escola duvidam que ele tenha cometido o crime...Será possível descobrir a verdade sobre o assassinato? será que foi ele quem realmente o cometeu? E sem saber nada sobre a sua vi'),
('Soretomo Time Leap ni Suru?', 'Or Do You Make Time Leap?, それともタイムリープにする?', 2, 'FINALIZADO', 6.65, '2022-02-04', '2022-03-01', 'https://static3.mangalivre.net/capas/KFKIZ1ob9x8N6Ue64YWOLg/14626/61fc85f18ae9cexternal_cover.jpg', 'Tokina (30) uma esposa recém-casada, afirma ser capaz de saltar no tempo, e seu marido Nobumichi (24) é cético. Ela parece ser uma esposa muito gentil que faz pleno uso dos saltos do tempo e faz tudo por ele, mas o melhor uso de seus saltos no tempo é aliviar sua própria frustração...?!');

insert into mangas_authors (manga, author) values (17, 33), (18, 33), (19, 33), (20, 33);

insert into mangas_categories (manga, category) values 
(20, 2), (20, 4), (20, 12)
(19, 6), (19, 2), (19, 22), (19, 26), (19, 12),
(18, 2), (18, 3), (18, 12), (18, 16),
(17, 2), (17, 4), (17, 12);

