<%@ attribute name="id" required="true"  %>
<%@ attribute name="value" required="false"  %>
<%@ attribute name="name" required="false"  %>

<input id="${id}" name="${id}" value="${value}" />
<script>
$("#${id}").datepicker({changeMonth: true,changeYear: true ,yearRange: "1940:2017",dateFormat: 'dd/mm/yy'});
$("#${value}").datepicker({changeYear: true ,dateFormat: 'dd/mm/yy'});
</script>
 