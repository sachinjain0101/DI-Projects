insert into pnet_dw.dimEmployee
select * from (select md5('1234'||'n'||'j') as Md5,'1234' as SSN,'n' as FirstName,'j' as LastName) a
where not exists (select 1 from pnet_dw.dimEmployee e where a.Md5 = e.Md5)


insert into pnet_dw.dimEmployee
select * from (select md5('${ssn}'||'${firstName}'||'${lastName}') as Md5
                     ,'${ssn}' as SSN
                     ,'${firstName}' as FirstName
                     ,'${lastName}' as LastName) a
where not exists (select 1 from pnet_dw.dimEmployee e where a.Md5 = e.Md5)

insert into pnet_dw.dimClientGroup
select * from (select md5('${client}'||'${groupCode}') as Md5
                     ,'${client}' as Client
                     ,'${groupCode}' as GroupCode) a
where not exists (select 1 from pnet_dw.dimClientGroup e where a.Md5 = e.Md5)

insert into pnet_dw.factTimeDetail
select * from
(select
md5('${client}'||'${groupCode}'||'${ssn}'||'${payrollPeriodEndDate}'||'${firstName}'||'${lastName}'||to_char(${siteNo}, 'FM999999999999999999')
||to_char(${deptNo}, 'FM999999999999999999')||'${deptName}'||'${clientDeptCode}'||'${costID}'
||'${assignmentNo}'||'${adjustmentCode}'||'${adjustmentName}'||to_char(${agencyNo}, 'FM999999999999999999')||'${aprvlStatus}'||'${assignmentStartDate}'||'${transDate}'
||to_char(${regHours}, 'FM999999999999999999')||to_char(${otHours}, 'FM999999999999999999')||to_char(${dtHours}, 'FM999999999999999999')
||to_char(${regDollars}, 'FM999999999999999999')||to_char(${otDollars}, 'FM999999999999999999')||to_char(${dtDollars}, 'FM999999999999999999')) as Md5
, '${client}' as Client
, '${groupCode}' as GroupCode
, '${ssn}' as SSN
, to_timestamp('${payrollPeriodEndDate}', 'YYYY-MM-DD HH24:MI:SS') as PayrollPeriodEndDate
, '${firstName}' as FirstName
, '${lastName}' as LastName
, coalesce(${siteNo},0) as SiteNo
, coalesce(${deptNo},0) as DeptNo
, '${deptName}' as DeptName
, '${clientDeptCode}' as ClientDeptCode
, '${costID}' as CostID
, '${assignmentNo}' as AssignmentNo
, '${adjustmentCode}' as AdjustmentCode
, '${adjustmentName}' as AdjustmentName
, coalesce(${agencyNo},0) as AgencyNo
, '${aprvlStatus}' as AprvlStatus
, to_timestamp('${assignmentStartDate}', 'YYYY-MM-DD HH24:MI:SS') as AssignmentStartDate
, to_timestamp('${transDate}', 'YYYY-MM-DD HH24:MI:SS') as TransDate
, coalesce(${regHours},0) as RegHours
, coalesce(${otHours},0) as OT_Hours
, coalesce(${dtHours},0) as DT_Hours
, coalesce(${regDollars},0) as RegDollars
, coalesce(${otDollars},0) as OT_Dollars
, coalesce(${dtDollars},0) as DT_Dollars) a
where not exists (select 1 from pnet_dw.factTimeDetail e where a.Md5 = e.Md5)


select to_timestamp('2016-08-08 16:30:15', 'YYYY-MM-DD HH24:MI:SS')


select * from pnet_dw.dimEmployee

select * from pnet_dw.dimClientGroup

select * from pnet_dw.factTimeDetail;





