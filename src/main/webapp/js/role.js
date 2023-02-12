$(document).ready(function(){
    //.ten_class
    //#ten_id
    $('.btn-xoa').click(function(){
    //logic code
    //$(this) dai dien cho chinh element ma minh dang click
        const id = $(this).attr('id')
        const This = $(this)
        $.ajax({
            method: 'GET',
            url: `http://localhost:8080/api/roles/delete?id=${id}`,
            // data:
        }).done(function(data){
            if(data.data){
                This.closest('tr').remove()
                console.log('Xoa thanh cong')
            }else{
                console.log('Xoa that bai')
            }
        })
    })
})