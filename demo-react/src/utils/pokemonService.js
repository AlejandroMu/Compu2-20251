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


export default request;
