create table if not exists `myDepartment`
(
    id          char(19)    not null primary key,
    name        varchar(20) not null,
    insert_time datetime    not null default current_timestamp,
    update_time datetime    not null default current_timestamp on update current_timestamp
);

create table if not exists `myUser`
(
    id            char(19)     not null primary key,
    name          varchar(10)  not null,
    number        varchar(15)  not null,
    password      varchar(65)  not null,
    description   varchar(200) null,
    department_id char(19)     not null,
    role          char(4)      not null,
    group_number  tinyint      null,
    student       json         null comment '{"teacherId", "teacherName", "queueNumber", "projectTitle"}',
    teacher       json         null comment '{"A", "C", "total"}',
    insert_time   datetime     not null default current_timestamp,
    update_time   datetime     not null default current_timestamp on update current_timestamp,

    unique (number),
    index ((cast(student ->> '$.teacherId' as char(19)) collate utf8mb4_bin)),
    index (department_id, role, group_number)
);

create table if not exists `myProcess`
(
    id             char(19)    not null primary key,
    name           varchar(20) not null,
    items          json        null comment '[{"number", "name", "point", "description"}]',
    point          tinyint     null,
    auth           char(5)     not null,
    department_id  char(19)    not null,
    student_attach json        null comment '[{"number", "name", "ext", "description"}]',
    insert_time    datetime    not null default current_timestamp,
    update_time    datetime    not null default current_timestamp on update current_timestamp,
    index (department_id)
);
# 数据库服务器和业务服务器可能存在时间差，导致业务变动的时间与数据库时间戳存在差异
create table if not exists `myProcess_score`
(
    id          char(19) not null primary key,
    student_id  char(19) not null,
    process_id  char(19) not null,
    teacher_id  char(19) not null,
    detail      json     not null comment '{"teacherName", "score", detail: [{"number", "score"}]}',
    insert_time datetime not null default current_timestamp,
    update_time datetime not null default current_timestamp on update current_timestamp,

    unique (process_id, student_id, teacher_id)
);

create table if not exists `myProcess_file`
(
    id          char(19)    not null primary key,
    detail      varchar(60) null,
    student_id  char(19)    not null,
    process_id  char(19)    not null,
    number      tinyint     not null,
    insert_time datetime    not null default current_timestamp,
    update_time datetime    not null default current_timestamp on update current_timestamp,

    unique (process_id, student_id, number)
);