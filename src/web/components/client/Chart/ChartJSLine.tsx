'use client';

import { MeterData } from '../../types/meterdata'

//import useSWR from 'swr';

import  'chartjs-adapter-luxon';

import {
    Chart as ChartJS,
    TimeScale,
    CategoryScale,
    LinearScale,
    PointElement,
    LineElement,
    Title,
    Tooltip,
    Legend,
    BarElement,
  } from 'chart.js'

import { Line  } from 'react-chartjs-2';

ChartJS.register(CategoryScale,
    LinearScale,
    TimeScale,
    PointElement,
    LineElement,
    Title,
    Tooltip,
    Legend,
    BarElement)

export const options = {
  responsive: true,
  
  interaction: {
    mode: "nearest",
    axis: "x",
    intersect: false
  },
  animation: false,

  plugins: {
    legend: {
      position: 'top' as const,
    },
    title: {
      display: true,
      text: 'Power consumption',
    },
  },
  scales: {
    x: {
        type: 'time',
        time: {
          unit: 'minute'
      }
    }
  }
};


export function chart_data(api_values: Array<MeterData> ) {
  //console.log(typeof api_values);

  return ( {
    labels: api_values.map(row => row.epochValue),
    datasets: [
      {
        label: 'P1 Meter',
        data: api_values.map(row => row.active_power_w),
        borderColor: 'rgb(255, 99, 132)',
        backgroundColor: 'rgba(255, 99, 132, 0.5)',
        radius: 0,
        borderWidth: 1,
        pointRadius: 1
      }
    ],

  })

} 


export function ChartTest({ api_data }) {


  let test = JSON.parse(api_data.value) as Array<MeterData>;
 
  return (
    <div className="w-full">

      <Line options={options} data={chart_data(test)} />
    </div>

  );

}