'use client';

import useSWR from 'swr';

import {
    Chart as ChartJS,
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
import { faker } from '@faker-js/faker';

ChartJS.register(CategoryScale,
    LinearScale,
    PointElement,
    LineElement,
    Title,
    Tooltip,
    Legend,
    BarElement)

export const options = {
  responsive: true,
  plugins: {
    legend: {
      position: 'right' as const,
    },
    title: {
      display: true,
      text: 'Chart.js Horizontal Bar Chart',
    },
  },
};

const labels = ['January', 'February', 'March', 'April', 'May', 'June', 'July'];

export const chart_data = {
  labels,
  datasets: [
    {
      label: 'Dataset 1',
      data: labels.map(() => faker.number.int({ min: -1000, max: 1000 })),
      borderColor: 'rgb(255, 99, 132)',
      backgroundColor: 'rgba(255, 99, 132, 0.5)',
    }
  ],
};

 
const fetcher = (...args) => fetch(...args).then((res) => res.json());
 

export function ChartTest() {
  const { data, error } = useSWR('/api/meter', fetcher);
 
  if (error) return <div>Failed to load</div>;
  if (!data) return <div>Loading...</div>;
 
  return (
    <div>
      <h1>{data.name}</h1>
      <Line options={options} data={chart_data} />
    </div>

  );

}