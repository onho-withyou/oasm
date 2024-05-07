/** chart.js 를 이용한 차트 생성/변경 함수 모음
 *
 * 차트 선언 함수
 * - 인수 : name (html 내 chart가 들어갈 canvas 선택자),
 *		    labels (label에 들어갈 데이터 이름 집합),
 *		    data (x축에 대응될 데이터 값 집합)
 * - return chart가 되므로 setChart 함수를 객체에 담아 다른 함수에 이용해주면 됨
 *
 *
 * 차트 데이터 변경 함수
 * - 인수 : chart (차트 선언 함수를 받았던 객체), map (차트에 넣어줄 데이터가 들어간 맵)
 * - 데이터를 지우고 넣는 것이 아니라 덮어씌우는 형식으로 되어있으며 현재 5개년 기준으로 되어있음
 * - 다른 기준으로 변경 시 함수에 if문 조건 추가하여 수정하거나 다른 함수를 만들 것
 *
 * 차트 타입 변경 함수
 * - 인수 : chart (차트 선언 함수를 받았던 객체), value (변경될 차트의 종류 - 공식 문서에는 type으로 찾을 수 있음)
 * - 막대형, 선형 등 모양 변경
 *
 */


function chartTypeChanger(chart, value) {
    // 인수로 chart 객체와 type value 값을 받아옴
    chart.config._config.type = value;
    chart.update();	// chart update
}

function changeChart(name, labels, data) {

    i = 0;
    if (labels == null) {
        labels = ['데이터1', '데이터2', '데이터3', '데이터4', '데이터5', '데이터6'];
    }
    if (data == null) {
        data = ['100', '20', '50', '80', '65', '41'];
    }

    let chart = new Chart(name, {
        type: 'bar',		// 그래프 type
        data: {
            labels: labels, // x축
            datasets: [
                {//데이터
                    label: '건수',	// 값 이름
                    fill: false,
                    data: data,	// x축 대응 값
                    backgroundColor: [
                        // 색상
                        'rgba(255, 99, 132, 1)',
                        'rgba(54, 162, 235, 1)',
                        'rgba(255, 206, 86, 1)',
                        'rgba(75, 192, 192, 1)',
                        'rgba(153, 102, 255, 1)',
                        'rgba(255, 159, 64, 1)'
                    ],
                    borderColor: [
                        // 경계선 색상
                        'rgba(255, 99, 132, 1)',
                        'rgba(54, 162, 235, 1)',
                        'rgba(255, 206, 86, 1)',
                        'rgba(75, 192, 192, 1)',
                        'rgba(153, 102, 255, 1)',
                        'rgba(255, 159, 64, 1)'
                    ],
                    borderWidth: 1 // 경계선 굵기
                }
            ]
        },
        options: {
            maintainAspectRatio: false,
            scales: {
                x: {
                    ticks: {
                        minRotation: 0,
                    }
                },
                y: {
                    beginAtZero: true	,	// 0부터 시작
                    title: {
                        size: 11,
                        family: "'Noto Sans KR', sans-serif",
                        weight: 500,
                    }
                }
            }
        }
    });
    return chart;
}

