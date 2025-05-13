import { Card, CardContent, CardMedia, Typography } from '@mui/material';
import { useEffect, useState } from 'react';
import { requestPokemonDetail } from '../utils/pokemonService';

const PokemonDetail = ({ pokemon }) => {
  const [currentPokemon, setPokemon] = useState({});

  useEffect(() => {
    requestPokemonDetail(pokemon).then((PokemonDetail) => {
      setPokemon(PokemonDetail);
    });
  }, [pokemon]);

  return (
    <Card sx={{ width: {xs: "100%", md:"50%"}, margin: '20px' }}>
      <CardMedia
        sx={{ height: 140 }}
        image={currentPokemon.img}
        title={currentPokemon.name}
      />
      <CardContent >
        <Typography gutterBottom variant='h5' component='div'>
          Lizard
        </Typography>
        <Typography variant='body2' sx={{ color: 'text.secondary' }}>
          Lizards are a widespread group of squamate reptiles, with over 6,000
          species, ranging across all continents except Antarctica
        </Typography>
      </CardContent>
    </Card>
  );
};

export default PokemonDetail;
