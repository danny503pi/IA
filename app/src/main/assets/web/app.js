/**
 * NÁHUAT VIVO - Motor JavaScript de la Aplicación Móvil
 * 
 * Funciones implementadas:
 * 1. Catálogo/Buscador bilingüe Náhuat-Español con filtro por categoría.
 * 2. Guardar o desmarcar palabras en favoritos (almacenado en localStorage).
 * 3. Tarjetas interactivas de práctica (Flashcards) con giro 3D y evaluación.
 */

// ==========================================================================
// 1. BASE DE DATOS LOCAL DE VOCABULARIO
// ==========================================================================
const VOCABULARY_DATA = [
  // Saludos y Cortesía
  {
    id: 1,
    nahuat: "Yeyek tunal",
    spanish: "Buenos días",
    phonetic: "[yé-yek tú-nal]",
    category: "Saludos",
    exampleNahuat: "Yeyek tunal, nupilawan!",
    exampleSpanish: "¡Buenos días, mis hijos!"
  },
  {
    id: 2,
    nahuat: "Yeyek tayua",
    spanish: "Buenas noches",
    phonetic: "[yé-yek ta-yú-a]",
    category: "Saludos",
    exampleNahuat: "Yeyek tayua, shiktali mutonal.",
    exampleSpanish: "Buenas noches, que descanses."
  },
  {
    id: 3,
    nahuat: "Ken tinemi?",
    spanish: "¿Cómo estás?",
    phonetic: "[ken ti-né-mi]",
    category: "Saludos",
    exampleNahuat: "Ken tinemi nujikawan?",
    exampleSpanish: "¿Cómo estás, hermano mío?"
  },
  {
    id: 4,
    nahuat: "Ni-yultuk, tlasojkamatik",
    spanish: "Estoy bien, gracias",
    phonetic: "[ni-yúl-tuk, tla-soj-ka-má-tik]",
    category: "Saludos",
    exampleNahuat: "Naja ni-yultuk, tlasojkamatik miak.",
    exampleSpanish: "Yo estoy bien, muchas gracias."
  },
  {
    id: 5,
    nahuat: "Tlasojkamatik miak",
    spanish: "Muchas gracias",
    phonetic: "[tla-soj-ka-má-tik mí-ak]",
    category: "Saludos",
    exampleNahuat: "Tlasojkamatik miak pal mukal.",
    exampleSpanish: "Muchas gracias por tu casa."
  },
  {
    id: 6,
    nahuat: "Ne timutastuk",
    spanish: "Hasta luego / Nos vemos",
    phonetic: "[ne ti-mu-tás-tuk]",
    category: "Saludos",
    exampleNahuat: "Ne timutastuk musta.",
    exampleSpanish: "Hasta luego, nos vemos mañana."
  },

  // Naturaleza
  {
    id: 7,
    nahuat: "Tunal",
    spanish: "Sol / Día",
    phonetic: "[tú-nal]",
    category: "Naturaleza",
    exampleNahuat: "Ne tunal sujsul kalaki tiut.",
    exampleSpanish: "El sol ya se oculta por la tarde."
  },
  {
    id: 8,
    nahuat: "Metzti",
    spanish: "Luna / Mes",
    phonetic: "[méts-ti]",
    category: "Naturaleza",
    exampleNahuat: "Ne metzti pepetaka tik ne tayua.",
    exampleSpanish = "La luna brilla en la noche."
  },
  {
    id: 9,
    nahuat: "At",
    spanish: "Agua",
    phonetic: "[at]",
    category: "Naturaleza",
    exampleNahuat: "Naja nikuni at sesek.",
    exampleSpanish: "Yo bebo agua fresca."
  },
  {
    id: 10,
    nahuat: "Tit",
    spanish: "Fuego",
    phonetic: "[tit]",
    category: "Naturaleza",
    exampleNahuat: "Ne tit shutla tik ne tekuil.",
    exampleSpanish: "El fuego arde en el fogón."
  },
  {
    id: 11,
    nahuat: "Tal",
    spanish: "Tierra / Suelo",
    phonetic: "[tal]",
    category: "Naturaleza",
    exampleNahuat: "Ne tutal tejemet tiktasujtat.",
    exampleSpanish: "Nuestra tierra nosotros la amamos."
  },

  // Animales
  {
    id: 12,
    nahuat: "Mistun",
    spanish: "Gato",
    phonetic: "[mís-tun]",
    category: "Animales",
    exampleNahuat: "Ne mistun kochituk pan ne kal.",
    exampleSpanish: "El gato está durmiendo sobre la casa."
  },
  {
    id: 13,
    nahuat: "Pelu / Chichi",
    spanish: "Perro",
    phonetic: "[pé-lu / chí-chi]",
    category: "Animales",
    exampleNahuat: "Ne nupelu sujsul wey.",
    exampleSpanish: "Mi perro es muy grande."
  },
  {
    id: 14,
    nahuat: "Tutut",
    spanish: "Pájaro / Ave",
    phonetic: "[tú-tut]",
    category: "Animales",
    exampleNahuat: "Ne tutut takwikat tik ne kwawit.",
    exampleSpanish: "El pájaro canta en el árbol."
  },
  {
    id: 15,
    nahuat: "Michin",
    spanish: "Pescado / Pez",
    phonetic: "[mí-chin]",
    category: "Animales",
    exampleNahuat: "Tik ne apan nemit miak michintin.",
    exampleSpanish: "En el río hay muchos peces."
  },

  // Familia
  {
    id: 16,
    nahuat: "Piltzin",
    spanish: "Niño / Hijo",
    phonetic: "[píl-tsin]",
    category: "Familia",
    exampleNahuat: "Ne piltzin mawiltia tik ne tayua.",
    exampleSpanish: "El niño juega al anochecer."
  },
  {
    id: 17,
    nahuat: "Noya / Nana",
    spanish: "Mamá / Madre",
    phonetic: "[nó-ya / ná-na]",
    category: "Familia",
    exampleNahuat: "Naja niktasujta nu-noya.",
    exampleSpanish: "Yo quiero mucho a mi mamá."
  },
  {
    id: 18,
    nahuat: "Tata",
    spanish: "Papá / Padre",
    phonetic: "[tá-ta]",
    category: "Familia",
    exampleNahuat: "Nu-tata kichiwa kalsun.",
    exampleSpanish: "Mi papá hace pantalones."
  },

  // Números
  {
    id: 19,
    nahuat: "Se",
    spanish: "Uno (1)",
    phonetic: "[se]",
    category: "Números",
    exampleNahuat: "Nikpia se kal.",
    exampleSpanish: "Tengo una casa."
  },
  {
    id: 20,
    nahuat: "Ume",
    spanish: "Dos (2)",
    phonetic: "[ú-me]",
    category: "Números",
    exampleNahuat: "Ume mistuntin mawiltiat.",
    exampleSpanish: "Dos gatos juegan."
  },
  {
    id: 21,
    nahuat: "Yey",
    spanish: "Tres (3)",
    phonetic: "[yey]",
    category: "Números",
    exampleNahuat: "Yey sitaltin pepetakat.",
    exampleSpanish: "Tres estrellas brillan."
  },
  {
    id: 22,
    nahuat: "Nawi",
    spanish: "Cuatro (4)",
    phonetic: "[ná-wi]",
    category: "Números",
    exampleNahuat: "Nawi ejkat nemit ka talpaktuk.",
    exampleSpanish: "Cuatro vientos hay sobre el mundo."
  },
  {
    id: 23,
    nahuat: "Makwil",
    spanish: "Cinco (5)",
    phonetic: "[mák-wil]",
    category: "Números",
    exampleNahuat: "Makwil tapaloltin.",
    exampleSpanish: "Cinco saludos."
  },

  // Vida Cotidiana
  {
    id: 24,
    nahuat: "Kal",
    spanish: "Casa / Hogar",
    phonetic: "[kal]",
    category: "Vida Cotidiana",
    exampleNahuat: "Niaw ka nukal.",
    exampleSpanish: "Voy hacia mi casa."
  },
  {
    id: 25,
    nahuat: "Tamal",
    spanish: "Tamal / Pan de maíz",
    phonetic: "[ta-mál]",
    category: "Vida Cotidiana",
    exampleNahuat: "Ne tamal sesek te welek.",
    exampleSpanish: "El tamal frío no es sabroso."
  },
  {
    id: 26,
    nahuat: "Nikmati",
    spanish: "Lo sé / Entiendo",
    phonetic: "[nik-má-ti]",
    category: "Vida Cotidiana",
    exampleNahuat: "Eje, naja nikmati tajtaketza náhuat.",
    exampleSpanish: "Sí, yo sé hablar náhuat."
  },
  {
    id: 27,
    nahuat: "Tlasojtla",
    spanish: "Amor / Querer",
    phonetic: "[tla-sój-tla]",
    category: "Vida Cotidiana",
    exampleNahuat: "Naja nimitztlasojtla miak.",
    exampleSpanish: "Yo te quiero mucho."
  }
];

// Categorías disponibles
const CATEGORIES = ["Todas", "Saludos", "Naturaleza", "Animales", "Familia", "Números", "Vida Cotidiana"];

// ==========================================================================
// 2. ESTADO GLOBAL DE LA APLICACIÓN
// ==========================================================================
const AppState = {
  activeTab: "catalog",
  searchQuery: "",
  selectedCategory: "Todas",
  // OJO: Set de IDs numéricos para evitar duplicados en favoritos
  favorites: new Set([1, 3, 5, 7, 9, 12]), 
  
  // Estado de sesión de Flashcards
  flashcards: {
    deck: [],
    currentIndex: 0,
    isFlipped: false,
    knownCount: 0,
    reviewCount: 0,
    isPracticingAll: false
  }
};

// ==========================================================================
// 3. PERSISTENCIA EN LOCALSTORAGE
// ==========================================================================
// Punto crítico: si localStorage falla o está deshabilitado en modo incógnito,
// manejamos un try/catch para que la app no colapse en consola.
function loadFavoritesFromStorage() {
  try {
    const raw = localStorage.getItem("nahuat_vivo_favorites");
    if (raw) {
      const parsed = JSON.parse(raw);
      if (Array.isArray(parsed)) {
        AppState.favorites = new Set(parsed);
      }
    }
  } catch (error) {
    console.warn("No se pudo leer de localStorage:", error);
  }
}

function saveFavoritesToStorage() {
  try {
    const arrayIds = Array.from(AppState.favorites);
    localStorage.setItem("nahuat_vivo_favorites", JSON.stringify(arrayIds));
  } catch (error) {
    console.warn("No se pudo escribir en localStorage:", error);
  }
}

// ==========================================================================
// 4. INICIALIZACIÓN Y EVENT LISTENERS
// ==========================================================================
document.addEventListener("DOMContentLoaded", () => {
  loadFavoritesFromStorage();
  renderCategories();
  renderCatalog();
  updateFavoritesBadge();
  setupNavigationTabs();
  setupSearchEvents();
  setupFlashcardEvents();
});

// ==========================================================================
// 5. NAVEGACIÓN ENTRE PESTAÑAS (Móvil)
// ==========================================================================
function setupNavigationTabs() {
  const tabs = document.querySelectorAll(".nav-tab");
  tabs.forEach(tab => {
    tab.addEventListener("click", () => {
      const target = tab.dataset.tab;
      switchTab(target);
    });
  });

  // Botón para saltar del catálogo a práctica desde banner
  const btnPracticeFavs = document.getElementById("btnStartPracticeFromFavs");
  if (btnPracticeFavs) {
    btnPracticeFavs.addEventListener("click", () => {
      initFlashcardDeck(false);
      switchTab("flashcards");
    });
  }

  const btnGoCatalog = document.getElementById("btnGoToCatalog");
  if (btnGoCatalog) {
    btnGoCatalog.addEventListener("click", () => switchTab("catalog"));
  }
}

function switchTab(tabName) {
  AppState.activeTab = tabName;

  // Actualizar botones de navegación
  document.querySelectorAll(".nav-tab").forEach(t => {
    t.classList.toggle("active", t.dataset.tab === tabName);
  });

  // Mostrar la sección correspondiente
  document.querySelectorAll(".view-section").forEach(sec => sec.classList.remove("active"));

  const targetSection = document.getElementById(
    tabName === "catalog" ? "viewCatalog" :
    tabName === "favorites" ? "viewFavorites" : "viewFlashcards"
  );
  if (targetSection) targetSection.classList.add("active");

  // Actualizar subtítulo del encabezado
  const sub = document.getElementById("headerSubtitle");
  if (sub) {
    sub.textContent = 
      tabName === "catalog" ? "Catálogo y Buscador Bilingüe" :
      tabName === "favorites" ? `Palabras para Repaso (${AppState.favorites.size})` :
      "Tarjetas de Práctica Interactiva";
  }

  // Renderizados bajo demanda según pestaña
  if (tabName === "catalog") {
    renderCatalog();
  } else if (tabName === "favorites") {
    renderFavorites();
  } else if (tabName === "flashcards") {
    // Si el mazo está vacío, inicializarlo
    if (AppState.flashcards.deck.length === 0) {
      initFlashcardDeck(AppState.favorites.size === 0);
    } else {
      updateCardUI();
    }
  }
}

// ==========================================================================
// 6. CATÁLOGO Y BUSCADOR (Función 1)
// ==========================================================================
function renderCategories() {
  const container = document.getElementById("categoriesContainer");
  if (!container) return;

  container.innerHTML = CATEGORIES.map(cat => `
    <button class="category-chip ${cat === AppState.selectedCategory ? 'active' : ''}" data-category="${cat}">
      ${cat}
    </button>
  `).join("");

  container.querySelectorAll(".category-chip").forEach(btn => {
    btn.addEventListener("click", () => {
      AppState.selectedCategory = btn.dataset.category;
      renderCategories();
      renderCatalog();
    });
  });
}

function setupSearchEvents() {
  const searchInput = document.getElementById("searchInput");
  const clearBtn = document.getElementById("clearSearchBtn");

  if (!searchInput) return;

  // Evento input para respuesta en tiempo real
  searchInput.addEventListener("input", (e) => {
    AppState.searchQuery = e.target.value;
    clearBtn.style.display = AppState.searchQuery.length > 0 ? "flex" : "none";
    renderCatalog();
  });

  clearBtn.addEventListener("click", () => {
    searchInput.value = "";
    AppState.searchQuery = "";
    clearBtn.style.display = "none";
    renderCatalog();
    searchInput.focus();
  });
}

function getFilteredWords() {
  const query = AppState.searchQuery.trim().toLowerCase();
  const category = AppState.selectedCategory;

  return VOCABULARY_DATA.filter(item => {
    const matchesCategory = (category === "Todas" || item.category === category);
    const matchesQuery = !query || 
      item.nahuat.toLowerCase().includes(query) ||
      item.spanish.toLowerCase().includes(query) ||
      item.phonetic.toLowerCase().includes(query);
    
    return matchesCategory && matchesQuery;
  });
}

function renderCatalog() {
  const list = document.getElementById("vocabularyList");
  const countLabel = document.getElementById("resultsCount");
  if (!list) return;

  const words = getFilteredWords();

  if (countLabel) {
    countLabel.textContent = `Mostrando ${words.size || words.length} ${words.length === 1 ? 'palabra' : 'palabras'}`;
  }

  if (words.length === 0) {
    list.innerHTML = `
      <div class="empty-state">
        <div class="empty-icon">🔍</div>
        <h3>No se encontraron palabras</h3>
        <p>Prueba buscando con otro término o selecciona "Todas" en las categorías.</p>
      </div>
    `;
    return;
  }

  list.innerHTML = words.map(item => createWordCardHTML(item)).join("");

  // Conectar los botones de estrella
  attachFavoriteListeners(list);
}

function createWordCardHTML(item) {
  const isFav = AppState.favorites.has(item.id);
  return `
    <article class="vocab-card" data-id="${item.id}">
      <div class="card-header">
        <span class="card-badge">${item.category}</span>
        <button class="star-btn ${isFav ? 'active' : ''}" data-id="${item.id}" title="${isFav ? 'Quitar de repaso' : 'Guardar en repaso'}">
          ⭐
        </button>
      </div>
      <h3 class="vocab-title">${item.nahuat}</h3>
      <span class="vocab-phonetic">${item.phonetic}</span>
      <p class="vocab-spanish">${item.spanish}</p>
      ${item.exampleNahuat ? `
        <div class="vocab-example">
          <p class="example-nahuat">“${item.exampleNahuat}”</p>
          <p class="example-spanish">${item.exampleSpanish}</p>
        </div>
      ` : ''}
    </article>
  `;
}

// ==========================================================================
// 7. FAVORITOS / REPASO (Función 2)
// ==========================================================================
function attachFavoriteListeners(parentElement) {
  parentElement.querySelectorAll(".star-btn").forEach(btn => {
    btn.addEventListener("click", (e) => {
      e.stopPropagation();
      // OJO: Convertir a Number porque data-id retorna string
      const id = parseInt(btn.dataset.id, 10);
      toggleFavorite(id);
    });
  });
}

function toggleFavorite(id) {
  if (AppState.favorites.has(id)) {
    AppState.favorites.delete(id);
  } else {
    AppState.favorites.add(id);
  }

  saveFavoritesToStorage();
  updateFavoritesBadge();

  // Si estamos en catálogo o favoritos, actualizar la vista
  if (AppState.activeTab === "catalog") {
    renderCatalog();
  } else if (AppState.activeTab === "favorites") {
    renderFavorites();
  }
}

function updateFavoritesBadge() {
  const badge = document.getElementById("favCountBadge");
  if (badge) {
    badge.textContent = `${AppState.favorites.size} ⭐`;
  }
}

function renderFavorites() {
  const list = document.getElementById("favoritesList");
  const banner = document.getElementById("favoritesBanner");
  const emptyState = document.getElementById("emptyFavorites");
  const bannerText = document.getElementById("favBannerText");

  if (!list) return;

  const favoriteWords = VOCABULARY_DATA.filter(item => AppState.favorites.has(item.id));

  if (favoriteWords.length === 0) {
    list.innerHTML = "";
    if (banner) banner.style.display = "none";
    if (emptyState) emptyState.style.display = "block";
    return;
  }

  if (emptyState) emptyState.style.display = "none";
  if (banner) {
    banner.style.display = "flex";
    if (bannerText) bannerText.textContent = `${favoriteWords.length} palabras listas para practicar.`;
  }

  list.innerHTML = favoriteWords.map(item => createWordCardHTML(item)).join("");
  attachFavoriteListeners(list);
}

// ==========================================================================
// 8. TARJETAS DE REPASO / FLASHCARDS (Función 3)
// ==========================================================================
function initFlashcardDeck(practiceAll = false) {
  let cards = [];
  if (practiceAll) {
    cards = [...VOCABULARY_DATA];
  } else {
    cards = VOCABULARY_DATA.filter(item => AppState.favorites.has(item.id));
    if (cards.length === 0) {
      cards = [...VOCABULARY_DATA]; // Fallback si no hay favoritos
      practiceAll = true;
    }
  }

  AppState.flashcards = {
    deck: cards,
    currentIndex: 0,
    isFlipped: false,
    knownCount: 0,
    reviewCount: 0,
    isPracticingAll: practiceAll
  };

  const finishedView = document.getElementById("deckFinishedView");
  if (finishedView) finishedView.style.display = "none";

  const emptyFlashcards = document.getElementById("emptyFlashcards");
  if (emptyFlashcards) emptyFlashcards.style.display = "none";

  updateCardUI();
}

function setupFlashcardEvents() {
  const cardElement = document.getElementById("flashcardElement");
  const btnFlip = document.getElementById("btnFlipCard");
  const btnNext = document.getElementById("btnNextCard");
  const btnPrev = document.getElementById("btnPrevCard");
  const btnKnown = document.getElementById("btnMarkKnown");
  const btnReview = document.getElementById("btnMarkReview");
  const btnShuffle = document.getElementById("btnShuffle");
  const btnRestart = document.getElementById("btnRestartSession");

  // Click en la tarjeta o botón para voltear
  if (cardElement) {
    cardElement.addEventListener("click", flipCard);
  }
  if (btnFlip) {
    btnFlip.addEventListener("click", (e) => {
      e.stopPropagation();
      flipCard();
    });
  }

  if (btnNext) btnNext.addEventListener("click", nextCard);
  if (btnPrev) btnPrev.addEventListener("click", prevCard);

  // Calificación
  if (btnKnown) {
    btnKnown.addEventListener("click", () => {
      AppState.flashcards.knownCount++;
      nextCard();
    });
  }

  if (btnReview) {
    btnReview.addEventListener("click", () => {
      AppState.flashcards.reviewCount++;
      // Auto-añadir a favoritos para repasarlo luego
      const current = getCurrentCard();
      if (current && !AppState.favorites.has(current.id)) {
        AppState.favorites.add(current.id);
        saveFavoritesToStorage();
        updateFavoritesBadge();
      }
      nextCard();
    });
  }

  if (btnShuffle) {
    btnShuffle.addEventListener("click", () => {
      shuffleDeck();
      updateCardUI();
    });
  }

  if (btnRestart) {
    btnRestart.addEventListener("click", () => {
      initFlashcardDeck(AppState.flashcards.isPracticingAll);
    });
  }

  // Botones de pantalla de finalización
  const btnFinishedRestart = document.getElementById("btnFinishedRestart");
  if (btnFinishedRestart) {
    btnFinishedRestart.addEventListener("click", () => {
      initFlashcardDeck(AppState.flashcards.isPracticingAll);
    });
  }

  const btnFinishedAll = document.getElementById("btnFinishedAll");
  if (btnFinishedAll) {
    btnFinishedAll.addEventListener("click", () => {
      initFlashcardDeck(true);
    });
  }

  const btnPracticeAllFromEmpty = document.getElementById("btnPracticeAllFromEmpty");
  if (btnPracticeAllFromEmpty) {
    btnPracticeAllFromEmpty.addEventListener("click", () => {
      initFlashcardDeck(true);
    });
  }
}

function getCurrentCard() {
  const { deck, currentIndex } = AppState.flashcards;
  return deck[currentIndex] || null;
}

function flipCard() {
  const inner = document.getElementById("flashcardInner");
  if (!inner) return;

  AppState.flashcards.isFlipped = !AppState.flashcards.isFlipped;
  // OJO: La clase .is-flipped dispara la rotación 3D en style.css
  inner.classList.toggle("is-flipped", AppState.flashcards.isFlipped);
}

function nextCard() {
  const { deck, currentIndex } = AppState.flashcards;
  if (currentIndex + 1 < deck.length) {
    AppState.flashcards.currentIndex++;
    AppState.flashcards.isFlipped = false;
    updateCardUI();
  } else {
    showFinishedDeck();
  }
}

function prevCard() {
  if (AppState.flashcards.currentIndex > 0) {
    AppState.flashcards.currentIndex--;
    AppState.flashcards.isFlipped = false;
    updateCardUI();
  }
}

function shuffleDeck() {
  const { deck } = AppState.flashcards;
  // Algoritmo de Fisher-Yates para mezcla uniforme sin sesgo
  for (let i = deck.length - 1; i > 0; i--) {
    const j = Math.floor(Math.random() * (i + 1));
    [deck[i], deck[j]] = [deck[j], deck[i]];
  }
  AppState.flashcards.currentIndex = 0;
  AppState.flashcards.isFlipped = false;
}

function updateCardUI() {
  const card = getCurrentCard();
  const inner = document.getElementById("flashcardInner");
  const progressText = document.getElementById("cardProgressText");
  const progressBar = document.getElementById("deckProgressBar");
  const deckModeBadge = document.getElementById("cardDeckModeBadge");
  const btnPrev = document.getElementById("btnPrevCard");

  if (!card) {
    showEmptyFlashcards();
    return;
  }

  // Reiniciar estado visual de volteo
  if (inner) {
    inner.classList.remove("is-flipped");
    AppState.flashcards.isFlipped = false;
  }

  // Anverso (Náhuat)
  document.getElementById("cardFrontCategory").textContent = card.category;
  document.getElementById("cardFrontWord").textContent = card.nahuat;
  document.getElementById("cardFrontPhonetic").textContent = card.phonetic;

  // Reverso (Español)
  document.getElementById("cardBackWord").textContent = card.spanish;
  document.getElementById("cardBackExampleNahuat").textContent = card.exampleNahuat ? `“${card.exampleNahuat}”` : "";
  document.getElementById("cardBackExampleSpanish").textContent = card.exampleSpanish || "";

  // Ocultar bloque de ejemplo si la palabra no tiene
  const exBox = document.getElementById("cardBackExampleContainer");
  if (exBox) exBox.style.display = card.exampleNahuat ? "block" : "none";

  // Metadatos y barra de progreso
  const total = AppState.flashcards.deck.length;
  const currentNum = AppState.flashcards.currentIndex + 1;
  if (progressText) progressText.textContent = `Tarjeta ${currentNum} de ${total}`;
  if (deckModeBadge) {
    deckModeBadge.textContent = AppState.flashcards.isPracticingAll ? "Mazo: Todo el Vocabulario" : "Mazo: Favoritos";
  }
  if (progressBar) {
    const pct = Math.round((currentNum / total) * 100);
    progressBar.style.width = `${pct}%`;
  }

  if (btnPrev) {
    btnPrev.disabled = AppState.flashcards.currentIndex === 0;
  }
}

function showFinishedDeck() {
  const finishedView = document.getElementById("deckFinishedView");
  if (!finishedView) return;

  document.getElementById("scoreKnown").textContent = AppState.flashcards.knownCount;
  document.getElementById("scoreReview").textContent = AppState.flashcards.reviewCount;
  finishedView.style.display = "block";
}

function showEmptyFlashcards() {
  const emptyState = document.getElementById("emptyFlashcards");
  if (emptyState) emptyState.style.display = "block";
}
