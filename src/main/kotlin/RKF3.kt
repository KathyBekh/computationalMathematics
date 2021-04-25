

fun adams002(x1: Double,x2:Double,y1: Double,h: Double,F: Double) {
    //Надо получит первые 2 значения методом Рунге-Кутты или ещё каким.
    val ode45 = RungeKuttaFehlburg(0.1, 1.0, 1.2)
    val firstValues = ode45.rkf45()

    var xo = x1 + 0.1
    val yo = y1

    //Реализация самого метода Адамса-Башфорте матлаб
//
//    while (x(i) < x2)
//                q(i) = feval(F, x(i), y(i)) * h;
//    dq(i - 1) = q(i) - q(i - 1);
//    d2q(i - 2) = dq(i - 1) - dq(i - 2);
//    y(i + 1) = y(i) + q(i) + 0.5 * dq(i - 1) + 5 / 12 * d2q(i - 2);
//    i = i + 1;
//    x(i) = x1 + (i - 1) * h;
//    end;
//    result = y

//    function result=Adams(x1,x2,y1,h,F,eps)
//    kontroll=0;ch=h;
//    while (kontroll==0)
//        kol=(x2-x1)/ch+1;
//    solh=zeros(kol);
//    sol2h=zeros(round(kol/2));
//    solh=Adams002(x1,x2,y1,ch,F);
//    sol2h=Adams002(x1,x2,y1,2*ch,F);
//    kontroll=1;
//    for i=1:kol/2
//    if (1/15*abs(solh(2*i)-sol2h(i))>eps) kontroll=0; end
//    end
//    if (kontroll==0) ch=ch/2;end
//
//    end
//
//
//    koll=(kol-1)/((x2-x1)/h);
//    for i=1:(kol-1)/koll+1
//    %     result(i)=sol2h(round((1+(i-1)*koll)/2));
//
//    result(i)=solh(round(1+(i-1)*koll));
//    end
//    result;

    // Вызов метода для рещения задачи матлаб
//    function lab4
//            F=@func;
//    result=Adams(0.2, 1.2, 0, 0.1,F ,1E-2)
//    result=Adams(0.2, 1.2, 0, 0.1,F ,1E-3)
//    result=Adams(0.2, 1.2, 0, 0.1,F ,1E-4);
}