import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import Pokemons from './components/ListProduct.jsx'
import './index.css'

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <Pokemons />
  </StrictMode>,
)
