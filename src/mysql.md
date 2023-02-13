- ### uuid_to_bin and bin_to_uuid
```sql
select *
from account_product ap
where ap.account_product_id=unhex(replace('0c33a509-f4ba-43fa-bd00-30ef46864061', '-', ''))
;

select hex(account_product_id)
from account_product
;
```