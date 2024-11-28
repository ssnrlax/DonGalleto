// Variables globales
let productos = [];
let carrito = [];
let sabores = [];
let tiposVenta = [];

// Función para cargar los sabores
function cargarSabores() {
    fetch('http://localhost:8080/DonGalleto/api/sabores/getAllSabores')
        .then(response => response.json())
        .then(data => {
            llenarSelects(data, null);
        })
        .catch(error => console.error('Error al cargar sabores:', error));
}

// Función para cargar los tipos de venta
function cargarTiposVenta() {
    fetch('http://localhost:8080/DonGalleto/api/tipoVenta/getAllTiposVenta')
        .then(response => response.json())
        .then(data => {
            llenarSelects(null, data);
        })
        .catch(error => console.error('Error al cargar tipos de venta:', error));
}

// Función para llenar los selects de sabor y tipo de venta
function llenarSelects(saboresData, tiposVentaData) {
    if (saboresData) {
        sabores = saboresData;
        const flavorSelect = document.getElementById("flavor");
        flavorSelect.innerHTML = "<option>Selecciona un sabor</option>";
        sabores.forEach(sabor => {
            const option = document.createElement("option");
            option.textContent = sabor.sabor;
            option.value = sabor.idSabor;
            flavorSelect.appendChild(option);
        });
    }

    if (tiposVentaData) {
        tiposVenta = tiposVentaData;
        const typeSelect = document.getElementById("type");
        typeSelect.innerHTML = "<option>Selecciona tipo de venta</option>";
        tiposVenta.forEach(tipo => {
            const option = document.createElement("option");
            option.textContent = tipo.tipo;
            option.value = tipo.idTipoVenta;
            typeSelect.appendChild(option);
        });
    }
}

// Agregar el producto a la tabla
function agregarAVenta() {
    const flavorSelect = document.getElementById("flavor");
    const typeSelect = document.getElementById("type");
    const quantityInput = document.getElementById("quantity");

    const saborSeleccionado = sabores.find(s => s.idSabor == flavorSelect.value);
    const tipoSeleccionado = tiposVenta.find(t => t.idTipoVenta == typeSelect.value);
    const cantidad = parseInt(quantityInput.value);

    if (!saborSeleccionado || !tipoSeleccionado || isNaN(cantidad) || cantidad <= 0) {
        alert("Selecciona un sabor, un tipo de venta, y una cantidad válida.");
        return;
    }

    const productosBody = document.getElementById("productosTable").querySelector("tbody");
    const filas = productosBody.querySelectorAll("tr");

    // Verificar si el producto ya existe en la tabla
    for (const fila of filas) {
        const celdaSabor = fila.cells[0].textContent; // Columna del sabor
        const celdaTipo = fila.cells[1].textContent;  // Columna del tipo de venta

        if (celdaSabor === saborSeleccionado.sabor && celdaTipo === tipoSeleccionado.tipo) {
            alert("El producto ya está en la tabla.");
            return;
        }
    }

    // Crear una nueva fila
    const fila = document.createElement("tr");

    const celdaSabor = document.createElement("td");
    celdaSabor.textContent = saborSeleccionado.sabor;

    const celdaTipo = document.createElement("td");
    celdaTipo.textContent = tipoSeleccionado.tipo;

    const celdaCantidad = document.createElement("td");

    const botonMenos = document.createElement("button");
    botonMenos.textContent = "-";
    botonMenos.addEventListener("click", () => {
        const nuevaCantidad = parseInt(inputCantidad.value) - 1;
        if (nuevaCantidad <= 0) {
            productosBody.removeChild(fila); // Eliminar la fila si la cantidad llega a 0
        } else {
            inputCantidad.value = nuevaCantidad;
            actualizarPrecio();
        }
    });

    const inputCantidad = document.createElement("input");
    inputCantidad.type = "number";
    inputCantidad.value = cantidad;
    inputCantidad.min = 1;
    inputCantidad.style.width = "50px";
    inputCantidad.readOnly = true; // Evitar que el usuario escriba manualmente

    const botonMas = document.createElement("button");
    botonMas.textContent = "+";
    botonMas.addEventListener("click", () => {
        const nuevaCantidad = parseInt(inputCantidad.value) + 1;
        inputCantidad.value = nuevaCantidad;
        actualizarPrecio();
    });

    celdaCantidad.appendChild(botonMenos);
    celdaCantidad.appendChild(inputCantidad);
    celdaCantidad.appendChild(botonMas);

    const celdaPrecio = document.createElement("td");
    const actualizarPrecio = () => {
        const precioTotal = (tipoSeleccionado.precio * parseInt(inputCantidad.value)).toFixed(2);
        celdaPrecio.textContent = `$${precioTotal}`;
    };
    actualizarPrecio();

    fila.appendChild(celdaSabor);
    fila.appendChild(celdaTipo);
    fila.appendChild(celdaCantidad);
    fila.appendChild(celdaPrecio);

    productosBody.appendChild(fila);

    flavorSelect.value = "";
    typeSelect.value = "";
    quantityInput.value = "";
}

// Mostrar y confirmar compra
document.addEventListener("DOMContentLoaded", () => {
    cargarSabores();
    cargarTiposVenta();

    const addButton = document.getElementById("add-button");
    const buyButton = document.getElementById("buy-button");
    const modal = document.getElementById("modal");
    const spanClose = modal.querySelector(".close");
    const confirmarCompra = document.getElementById("confirmarCompra");
    const dineroRecibidoInput = document.getElementById("dineroRecibido");
    const cambioOutput = document.getElementById("cambio");
    const totalVentaOutput = document.getElementById("totalVenta");
    const descuentoSelect = document.getElementById("descuento");
    const imprimirCheckbox = document.getElementById("imprimirTicket");

    let totalVenta = 0;

    // Mostrar el modal al hacer clic en "Comprar"
    buyButton.addEventListener("click", () => {
        const productosBody = document.getElementById("productosTable").querySelector("tbody");
        const filas = productosBody.querySelectorAll("tr");

        totalVenta = 0;

        // Calcula el total sumando el precio de cada fila
        filas.forEach(fila => {
            const precio = parseFloat(fila.cells[3].textContent.replace("$", ""));
            totalVenta += precio;
        });

        // Actualiza el total en el modal
        totalVentaOutput.textContent = `$${totalVenta.toFixed(2)}`;

        // Limpia los valores del modal
        dineroRecibidoInput.value = "";
        cambioOutput.textContent = "$0.00";
        descuentoSelect.value = "0"; // Sin descuento
        imprimirCheckbox.checked = false;

        // Muestra el modal
        modal.style.display = "block";
    });

    // Ocultar el modal al hacer clic en la "X" o fuera del modal
    spanClose.addEventListener("click", () => {
        modal.style.display = "none";
    });

    window.addEventListener("click", (event) => {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    });

    // Actualizar el cambio en tiempo real y aplicar descuentos
    function actualizarCambioYDescuento() {
        const dineroRecibido = parseFloat(dineroRecibidoInput.value);
        const descuento = parseFloat(descuentoSelect.value);
        const totalConDescuento = totalVenta - (totalVenta * (descuento / 100));
        
        totalVentaOutput.textContent = `$${totalConDescuento.toFixed(2)}`;

        if (!isNaN(dineroRecibido) && dineroRecibido >= totalConDescuento) {
            const cambio = dineroRecibido - totalConDescuento;
            cambioOutput.textContent = `$${cambio.toFixed(2)}`;
        } else {
            cambioOutput.textContent = "$0.00";
        }
    }

    dineroRecibidoInput.addEventListener("input", actualizarCambioYDescuento);
    descuentoSelect.addEventListener("change", actualizarCambioYDescuento);

    // Confirmar compra
    confirmarCompra.addEventListener("click", () => {
        const dineroRecibido = parseFloat(dineroRecibidoInput.value);
        const descuento = parseFloat(descuentoSelect.value);
        const totalConDescuento = totalVenta - (totalVenta * (descuento / 100));

        if (isNaN(dineroRecibido) || dineroRecibido < totalConDescuento) {
            alert("El dinero recibido no es suficiente.");
            return;
        }

        if (imprimirCheckbox.checked) {
            generarPDF(dineroRecibido, totalConDescuento, descuento);
        } else {
            alert("No se imprimirá el ticket.");
        }

        // Limpia la tabla y cierra el modal
        const productosBody = document.getElementById("productosTable").querySelector("tbody");
        productosBody.innerHTML = "";
        modal.style.display = "none";
    });

    addButton.addEventListener("click", agregarAVenta);

    // Función para generar el PDF
    function generarPDF(dineroRecibido, totalConDescuento, descuento) {
        const { jsPDF } = window.jspdf;
        const doc = new jsPDF();

        // Agregar el contenido del ticket al PDF
        doc.setFont("helvetica", "normal");
        doc.text("Venta Realizada", 10, 10);
        doc.text(`Total de la Venta: $${totalVenta.toFixed(2)}`, 10, 20);
        doc.text(`Descuento Aplicado: ${descuento}%`, 10, 30);
        doc.text(`Total con Descuento: $${totalConDescuento.toFixed(2)}`, 10, 40);
        doc.text(`Dinero Recibido: $${dineroRecibido.toFixed(2)}`, 10, 50);
        doc.text(`Cambio: $${(dineroRecibido - totalConDescuento).toFixed(2)}`, 10, 60);

        // Generar el archivo PDF
        doc.save("ticket_venta.pdf");
    }
});

