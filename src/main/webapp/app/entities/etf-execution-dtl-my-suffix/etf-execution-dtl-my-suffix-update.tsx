import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './etf-execution-dtl-my-suffix.reducer';
import { IEtfExecutionDtlMySuffix } from 'app/shared/model/etf-execution-dtl-my-suffix.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IEtfExecutionDtlMySuffixUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IEtfExecutionDtlMySuffixUpdateState {
  isNew: boolean;
}

export class EtfExecutionDtlMySuffixUpdate extends React.Component<
  IEtfExecutionDtlMySuffixUpdateProps,
  IEtfExecutionDtlMySuffixUpdateState
> {
  constructor(props) {
    super(props);
    this.state = {
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentWillUpdate(nextProps, nextState) {
    if (nextProps.updateSuccess !== this.props.updateSuccess && nextProps.updateSuccess) {
      this.handleClose();
    }
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { etfExecutionDtlEntity } = this.props;
      const entity = {
        ...etfExecutionDtlEntity,
        ...values
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/etf-execution-dtl-my-suffix');
  };

  render() {
    const { etfExecutionDtlEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="smartEtfApp.etfExecutionDtl.home.createOrEditLabel">Create or edit a EtfExecutionDtl</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : etfExecutionDtlEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="etf-execution-dtl-my-suffix-id">ID</Label>
                    <AvInput id="etf-execution-dtl-my-suffix-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="basketOrderIdLabel" for="etf-execution-dtl-my-suffix-basketOrderId">
                    Basket Order Id
                  </Label>
                  <AvField
                    id="etf-execution-dtl-my-suffix-basketOrderId"
                    type="string"
                    className="form-control"
                    name="basketOrderId"
                    validate={{
                      number: { value: true, errorMessage: 'This field should be a number.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="transactionIdLabel" for="etf-execution-dtl-my-suffix-transactionId">
                    Transaction Id
                  </Label>
                  <AvField id="etf-execution-dtl-my-suffix-transactionId" type="string" className="form-control" name="transactionId" />
                </AvGroup>
                <AvGroup>
                  <Label id="transactionTypeLabel" for="etf-execution-dtl-my-suffix-transactionType">
                    Transaction Type
                  </Label>
                  <AvField id="etf-execution-dtl-my-suffix-transactionType" type="text" name="transactionType" />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/etf-execution-dtl-my-suffix" replace color="info">
                  <FontAwesomeIcon icon="arrow-left" />
                  &nbsp;
                  <span className="d-none d-md-inline">Back</span>
                </Button>
                &nbsp;
                <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                  <FontAwesomeIcon icon="save" />
                  &nbsp; Save
                </Button>
              </AvForm>
            )}
          </Col>
        </Row>
      </div>
    );
  }
}

const mapStateToProps = (storeState: IRootState) => ({
  etfExecutionDtlEntity: storeState.etfExecutionDtl.entity,
  loading: storeState.etfExecutionDtl.loading,
  updating: storeState.etfExecutionDtl.updating,
  updateSuccess: storeState.etfExecutionDtl.updateSuccess
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(EtfExecutionDtlMySuffixUpdate);
