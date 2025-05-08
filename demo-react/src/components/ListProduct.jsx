import { useEffect, useState } from "react";
import request from "../utils/pokemonService";

const Pokemons = () => {

    const [pokemons, setPokemons] = useState([])
    const [page, setPage] = useState(0)

    
    const updatePage = (currentPage) => {

        request((pokemons) =>{
            setPokemons(pokemons)
        }, currentPage);
    }
    useEffect(() => {
        updatePage(0);
    },[])

    return (
        <div className="list-product">
            <h1>Mis productos</h1>
            {
                pokemons.map(
                    (p) => <div><p>{p.name}</p></div>
                )
            }
            <button onClick={() =>{
                setPage(page-1)
                updatePage(page-1)
            }}>Atras</button>
            <button onClick={() =>{
                setPage(page+1)
                updatePage(page+1)
            }}>Siguiente</button>

        </div>
    );
}

export default Pokemons;