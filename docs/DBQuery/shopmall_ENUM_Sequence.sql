create type enum_order_status as enum('�Ա���','�ԱݿϷ�','�����Ϸ�','��ǰ�غ���','�κ����Ϸ�','���Ϸ�','�κй����','�����','��ۿϷ�','���','�κ����');
create type enum_ordpro_status as enum('�Ա���','�ԱݿϷ�','�����Ϸ�','��ǰ�غ���','���Ϸ�','�����','��ۿϷ�','���');
create type enum_member_role as enum('ROLE_USER','ROLE_ADMIN');

create sequence seq_member start 1;
create sequence seq_product start 1;
create sequence seq_option start 1;
create sequence seq_optDtail start 1;
create sequence seq_prodimg start 1;
create sequence seq_prodinven start 1;
create sequence seq_category start 1;
create sequence seq_order start 1;
create sequence seq_ordpay start 1;
create sequence seq_delivers start 1;


