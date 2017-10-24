drop table pnet_dw.factTimeDetail;
create table pnet_dw.factTimeDetail
(
    Md5 text
  , Client varchar(20)
  , GroupCode varchar(20)
  , SSN varchar(20)
  , PayrollPeriodEndDate timestamp
  , FirstName varchar(20)
  , LastName varchar(20)
  , SiteNo int
  , DeptNo int
  , DeptName varchar(30)
  , ClientDeptCode varchar(100)
  , CostID varchar(30)
  , AssignmentNo varchar(32)
  , AdjustmentCode varchar(3)
  , AdjustmentName varchar(10)
  , AgencyNo integer
  , AprvlStatus char(4)
  , AssignmentStartDate timestamp
  , TransDate timestamp
  , RegHours decimal(5, 2)
  , OT_Hours decimal(5, 2)
  , DT_Hours decimal(5, 2)
  , RegDollars decimal(7, 2)
  , OT_Dollars decimal(7, 2)
  , DT_Dollars decimal(7, 2)
);

create index idx1_fct_md5 on pnet_dw.factTimeDetail (Md5);

drop table pnet_dw.dimClientGroup;
create table pnet_dw.dimClientGroup
(
    Md5 text
  , Client varchar(20)
  , GroupCode varchar(20)
);

create index idx1_cg_md5 on pnet_dw.dimClientGroup (Md5);

drop table pnet_dw.dimEmployee;
create table pnet_dw.dimEmployee
(
    Md5 text
  , SSN varchar(20)
  , FirstName varchar(20)
  , LastName varchar(20)
);

create index idx1_emp_md5 on pnet_dw.dimEmployee (Md5);
