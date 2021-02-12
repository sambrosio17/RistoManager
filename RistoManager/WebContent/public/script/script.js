window.addEventListener('load', function() {
	console.log('All assets are loaded')
})

function openMenu() {

	console.log("ciao");
	const sideMenu=document.getElementsByClassName("side_menu")[0];
	sideMenu.style.left="0%";

}

function closeMenu(){

	console.log("wwwwwee");
	const sideMenu=document.getElementsByClassName("side_menu")[0];
	sideMenu.style.left="-100%";

}

function goToRiepilogo(){
	location.href="./riepilogo";
}

function goToComanda(){
	location.href="./comanda";
}

async function showDetails(product){

	const productDetails=document.querySelector(".product_details");

	const response= await fetch("./prodotto?id="+product);
	const data= await response.json();
	console.log(data);


	const title=document.querySelector(".product_details h2");
	const subtitle=document.querySelector(".product_details h3");
	const list=document.querySelector(".product_details ol");

	list.innerHTML="";
	title.innerText=data.nomeprodotto;

	let li=document.createElement("li");

	for(let i of data.ingredienti){
		console.log(i);
		li.innerText=i;
		list.appendChild(li);
		li=document.createElement("li");
	}

	title.style.transition="1s all ease";
	subtitle.style.transition="1s all ease";
	list.style.transition="1s all ease";

	productDetails.style.visibility="visible";
	productDetails.style.opacity="100%"
		productDetails.style.width="350px";
	productDetails.style.height="350px";

	title.style.opacity="100%";
	subtitle.style.opacity="100%";
	list.style.opacity="100%";
}


async function genera(){
	
	let codicePlaceholder=document.querySelector("#codice");
	const response= await fetch("../generatore");
	const data= await response.json();

	codicePlaceholder.innerText=data;
}

function closeDetails(){

	console.log("weeee");

	const element=document.querySelector(".product_details");
	const title=document.querySelector(".product_details h2");
	const subtitle=document.querySelector(".product_details h3");
	const list=document.querySelector(".product_details ol");

	title.style.transition="0.3s all ease";
	subtitle.style.transition="0.3s all ease";
	list.style.transition="0.3s all ease";

	title.style.opacity="0%";
	subtitle.style.opacity="0%";
	list.style.opacity="0%";

	element.style.opacity="0%"
		element.style.height="0px";
	element.style.width="0px";
	element.style.visibility="hidden";

}


function done(element) {

	if( element.style.textDecoration=="line-through"){
		element.style.textDecoration="none";
	}else {
		element.style.textDecoration="line-through";
	}
}

function removeItem(id){
	
	location.href="./removeComanda?productId="+id;
}