insert into roles (name) values ('admin');

insert into rules (name) values ('read_item');
insert into rules (name) values ('write_item');

insert into role_rule_links (id_role, id_rule) values (1, 1);
insert into role_rule_links (id_role, id_rule) values (1, 2);

insert into users (login, password, name) values ('johnD', '12345678', 'John Doe');

insert into states (name) values ('completed');
insert into states (name) values ('new');

insert into categories (name) values ('high');
insert into categories (name) values ('low');
insert into categories (name) values ('normal');

insert into items (description, text, id_category, id_state, id_user)
values ('computer doen''t work', 'when i came at the office today my computer was off', 1, 1, 1);

insert into attaches (name, id_item) values ('picture of the monitor', 1);
insert into comments (comment, id_item) values ('the electrical plug was out the socket. i''ve plug it in', 1);