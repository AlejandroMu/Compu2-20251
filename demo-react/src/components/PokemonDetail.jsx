const PokemonDetail = ({pokemon}) => {

    return (
        <div className='product-detail'>
            <h1>{pokemon.name}</h1>
            <h3>{pokemon.description}</h3>
            <img src={pokemon.imageUrl} alt={pokemon.name} />
            <p>Price: ${pokemon.price}</p>
        </div>
    );
};

export default PokemonDetail;
