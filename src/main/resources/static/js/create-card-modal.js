import { buildCard, showError } from './card-modal.js';
import { validateText } from "./validate-fields.js";

export async function loadCreateModal() {
  const container = document.getElementById("createmodalContainer");
  const html = await fetch("create-card-modal.html").then(res => res.text());
  const style = document.createElement("link");
  style.rel = "stylesheet";
  style.href = "styles/create-card-modal.css";
  document.head.appendChild(style);

  container.innerHTML = html;

  document.getElementById("cancelBtnCreateModal").addEventListener("click", closeModal);
  document.getElementById("closeCreateModal").addEventListener("click", closeModal);
  document.getElementById("confirmBtnCreateModal").addEventListener("click", async () => {
    const erro = checkFields();
    if(erro) return alert(erro);

    createCard();
  });
}

let cardListRef = null;
let boardIdRef = null;
let columnRef = null;

export function openModal(cardList, column) {
  cardListRef = cardList;
  boardIdRef = column.id;
  columnRef = column;

  document.getElementById("cardCreateTitleInput").value = "";
  document.getElementById("cardCreateDescInput").value = "";
  document.getElementById("cardCreateModal").style.display = "flex";
}

function closeModal() {
  document.getElementById("cardCreateModal").style.display = "none";
}

function checkFields(){
  let errors = validateText("cardCreateTitleInput", 1, 50, "Título");
  errors += validateText("cardCreateDescInput", 1, 1000, "Descrição");
  return errors;
}

async function createCard() {
  const title = document.getElementById("cardCreateTitleInput").value;
  const description = document.getElementById("cardCreateDescInput").value;

  const response = await fetch("http://localhost:8080/card", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ title, description, boardId: boardIdRef }),
  });

  const card = await response.json();

  if (!card.success)
    return showError(card.errors);

  buildCard(cardListRef, card.result, columnRef)
  closeModal();
}