const request = (callback, pag = 0) => {
  const limit = 5;
  fetch(`https://pokeapi.co/api/v2/pokemon?offset=${limit * pag}&limit=${limit}`)
    .then((response) => {
      if (response.ok) {
        return response.json();
      } else {
        throw new Error('Network response was not ok');
      }
    })
    .then((data) => {
      const pokemons = data.results.map((pokemon, index) => ({
        id: index + 1,
        name: pokemon.name,
        urlDetail: pokemon.url,
      }));
      callback(pokemons);
    });
    ///dasda
};

const requestPokemonDetail = async (pokemon) =>{
    const response = await fetch(pokemon.urlDetail);
    const data = await response.json()
    const pokemonObj = {
     id:data.id,
     img:data.sprites.back_default,
     name:pokemon.name,
     experience: data.base_experience,
     height: data.height,
     weight: data.weight
    }
    return pokemonObj


}

export {requestPokemonDetail};

export default request;
