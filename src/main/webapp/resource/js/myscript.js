/**
 * Created by ivanybma on 4/6/16.
 */
function sumc(itema, itemb){
    return itema+itemb;
}

function Delete(){
    var form = document.getElementById("deleteProfileForm");
    form.submit();
    alert("ok");
}

function testprompt(){
   // var reply = prompt("testing prompt, type something here...","");
    //alert(reply);
    //reply = confirm("confirmed?");
    //alert(reply);

   outer: for(var i=0; i<10; i++)
    {
        inner: for(var j=0; j<5; j++)
        {
           // alert(i+" "+ j);

            if(i==2){
                //alert("current".toUpperCase()+" i="+i);
                //alert("2>\"4\"?"+ (2>"4"));
                //alert(0.1 + 0.2 == 0.3);
                //alert((0.1+0.2).toFixed(10)+ '0.3');
                //alert(Math.floor(3.1));
                //alert(Math.floor(-3.1));
                //alert(Math.ceil(3.1));
                //alert(Math.ceil(-3.1));
                //alert(Math.round(3.1));
                //alert(Math.round(-3.1));

                break outer;
            }
            break inner;

        }
    }
    var newobj = {

    }
    alert(newobj.notexist);
    var obj={
        count: 10,
        price: 13,
        name: "test"
    }
    doubleObj(obj);
    alert("count: "+obj.count+" price: "+ obj.price+" name: " + obj.name );

    var summator = {
        name: "funcobj_a",
        run: function(){
            var numa = prompt("input first number",0);
            var numb = prompt("input second number",0);
            alert(this.sum(numa, numb));
        },
        sum: function(a,b){
            return parseInt(a)+parseInt(b);
        }
    }
summator.run();
    var summatorobj = new Summator();
    summatorobj.run();
}

function doubleObj(obj){
    for(var k in obj){
        var val=obj[k];
        if(!isNaN(parseFloat(val))&&isFinite(val)){
            obj[k]*=2;
        }
    }
}

function Summator(){
    this.run= function(){
        var numa = prompt("input first number",0);
        var numb = prompt("input second number",0);
        alert(this.sum(numa, numb));
    },
    this.sum= function(a,b){
        return parseInt(a)+parseInt(b);
    }
}

