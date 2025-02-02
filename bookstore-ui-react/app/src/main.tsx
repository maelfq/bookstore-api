import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
//import App from './App.tsx'
import { BookStoreIndex } from './BookstoreHomepage.tsx'

createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <BookStoreIndex/>
  </StrictMode>
)
    /*<BookstoreHomepage/> */
    /*<App />*/
