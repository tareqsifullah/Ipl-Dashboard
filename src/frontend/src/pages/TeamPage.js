import {React,useEffect,useState} from 'react';
import {useParams,Link} from 'react-router-dom';
import{MatchDetailCard} from '../components/MatchDetailCard';
import{MatchSmallCard} from '../components/MatchSmallCard';
import { PieChart } from 'react-minimal-pie-chart';
import './TeamPage.scss';
export const TeamPage = () => {
//initialization matches:[] helped reduce the error of team.matches is undefined
    const [team,setTeam] = useState({matches:[]});
   //Using destructuring to get the teamName
    const {teamName} = useParams();

    useEffect(
        () => {
            const fetTeams = async() =>{
                const response = await fetch( `http://localhost:8080/team/${teamName}`);
                const data = await response.json();
                console.log(data);
                setTeam(data);
            };
            fetTeams();
        },[teamName]
    );

  if(!team || !team.teamName){
    return <h1>Team Not Found</h1>
  }

  return (
    <div className="TeamPage">
     <div class = "team-name-section">
     <h1 className = "team-name">{team.teamName}</h1>
     </div>
     <div className = "win-loss-section">
     Wins/Loses
     <PieChart
       data={[
         { title: 'Losses', value: team.totalMatches-team.totalWins, color: '#e15454' },
         { title: 'Wins', value: team.totalWins, color: '#4da375' },


       ]}
     />
     </div>
     <div className = "match-detail-section">
              <h3>Latest Matches</h3>
              <MatchDetailCard teamName = {team.teamName} match = {team.matches[0]}/></div>
                {team.matches.slice(1).map(match => <MatchSmallCard teamName = {team.teamName} match = {match}/>)}
     <div className = "more-link">
           <Link to={`/teams/${teamName}/matches/${process.env.REACT_APP_DATA_END_YEAR}`}>More ></Link>
</div>
    </div>
  );
}
