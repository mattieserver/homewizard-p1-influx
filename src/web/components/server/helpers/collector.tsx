export function createCollectorURL(): string {
    const url = process.env.COLLECTOR_PROTO + '://' + process.env.COLLECTOR_HOST + ':' + process.env.COLLECTOR_PORT
    return url;
}

export async function getInitialMeterData() {
    let url = createCollectorURL();
    url = url + '/live/1h-data/'
    const res = await fetch(url);
    const data = await res.json();
   
    if (!data) {
      return {
        notFound: true,
      };
    }
   
    return data;
  }
  