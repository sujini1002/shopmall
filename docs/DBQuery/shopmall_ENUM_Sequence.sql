create type enum_order_status as enum('입금전','입금완료','결제완료','상품준비중','부분출고완료','출고완료','부분배송중','배송중','배송완료','취소','부분취소');
create type enum_ordpro_status as enum('입금전','입금완료','결제완료','상품준비중','출고완료','배송중','배송완료','취소');
create type enum_member_role as enum('MEMBER','ADMIN');

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


