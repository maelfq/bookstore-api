import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
//import App from './App.tsx'
import { BookstoreIndex } from './BookstoreHomepage.tsx'

createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <BookstoreIndex/>
  </StrictMode>
)
    /*<BookstoreHomepage/> */
    /*<App />*/
