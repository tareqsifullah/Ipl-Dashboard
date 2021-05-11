import {React,useEffect,useState} from 'react';
import {useParams} from 'react-router-dom';
import{MatchDetailCard} from '../components/MatchDetailCard';

export const MatchPage = () => {

    const [matches ,setMatches] = useState([]);
    const {teamName,year} = useParams();
    useEffect(
            () => {
                const fetchMatches = async() =>{
                    const response = await fetch( `http://localhost:8080/team/${teamName}/matches?year=${year}` );
                    const data = await response.json();
                    console.log(data);
                    setMatches(data);
                };
                fetchMatches();
            },[]
        );

if(!matches){
    return <h1>Match Not Found</h1>
  }

  return (
    <div className="MatchPage">
        <h1>MatchPage</h1>
        {
          matches.map(match => <MatchDetailCard teamName={teamName} match = {match}/>)

        }

    </div>
  );
}