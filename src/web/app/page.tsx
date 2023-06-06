import { ChartTest } from '../components/client/Chart/ChartJSLine'
import { createCollectorURL, getInitialMeterData } from '../components/server/helpers/collector'


export default function Home() {
  const apiData = getInitialMeterData();
  return (
    <main className="flex min-h-screen flex-col items-center justify-between p-24">
    
      <ChartTest api_data={ apiData }/>
    </main>
  )
}
